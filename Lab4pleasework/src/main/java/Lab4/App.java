package Lab4;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class App implements ActionListener {
    Transform3D pencilTransform = new Transform3D();
    Vector3d euler = new Vector3d(0.0, 0.0, 0.0);
    TransformGroup pencilGroup;

    public static void main(String[] args) {
        App app = new App();
        app.init();
    }

    public void init() {
        new Timer(25, this).start();

        SimpleUniverse universe = new SimpleUniverse();
        universe.getViewingPlatform().setNominalViewingTransform();

        BranchGroup pencil = makePencil();
        TransformGroup tfg = new TransformGroup();
        tfg.addChild(pencil);
        tfg.setTransform(pencilTransform);
        tfg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        pencilGroup = tfg;

        BranchGroup group = new BranchGroup();
        group.addChild(tfg);
        group.addChild(makeLight());
        universe.addBranchGraph(group);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pencilGroup != null) {
            euler.setZ(euler.getZ() + 0.01);
            euler.setX(euler.getX() + 0.01);
            pencilTransform.setEuler(euler);
            pencilTransform.setScale(0.25);
            pencilGroup.setTransform(pencilTransform);
        }
    }

    private static BranchGroup makePencil() {
        BranchGroup train = new BranchGroup();
        
        Cylinder base = new Cylinder(0.5f, 1.25f);
        Box cabin = new Box(0.5f,0.5f,0.5f,null);
        Cylinder wheel1 = new Cylinder(0.2f, 0.1f);
        Cylinder wheel2 = new Cylinder(0.2f, 0.1f);
        Cylinder wheel3 = new Cylinder(0.2f, 0.1f);
        Cylinder wheel4 = new Cylinder(0.2f, 0.1f);

        Transform3D tf = new Transform3D();


        base.setAppearance(makeColor(new Color3f(1.0f, 0.1f, 0.0f), false));
        cabin.setAppearance(makeColor(new Color3f(1.0f, 1.0f, 0.0f), false));
        wheel1.setAppearance((makeColor(new Color3f(0.25f, 0.25f, 1.0f), false)));
        wheel2.setAppearance((makeColor(new Color3f(0.25f, 0.25f, 1.0f), false)));
        wheel3.setAppearance((makeColor(new Color3f(0.25f, 0.25f, 1.0f), false)));
        wheel4.setAppearance((makeColor(new Color3f(0.25f, 0.25f, 1.0f), false)));

        train.addChild(applyTransform(base,tf));
        tf.setTranslation(new Vector3d(0.0, 1.1, 0.0));
        train.addChild(applyTransform(cabin,tf));
        tf.setEuler(new Vector3d(1.570796326794896619, 0.0, 0.0));
        tf.setTranslation(new Vector3d(0.5, 0.0, 0.5));
        train.addChild(applyTransform(wheel1,tf));
        tf.setTranslation(new Vector3d(0.5, 0.0, -0.5));
        train.addChild(applyTransform(wheel2,tf));
        tf.setTranslation(new Vector3d(0.5, 1.1, -0.8));
        train.addChild(applyTransform(wheel3,tf));
        tf.setTranslation(new Vector3d(0.5, 1.1, 0.8));
        train.addChild(applyTransform(wheel4,tf));


        return train;
    }

    private static TransformGroup applyTransform(Node object, Transform3D tf) {
        TransformGroup group = new TransformGroup();
        group.setTransform(tf);
        group.addChild(object);
        return group;
    }

    private static Appearance makeColor(Color3f color, boolean specular) {
        Appearance a = new Appearance();
        a.setMaterial(new Material(color, new Color3f(), color, specular ? color : new Color3f(), 1.0f));
        return a;
    }

    private static DirectionalLight makeLight() {
        Color3f lightColor = new Color3f(1.0f, 1.0f, 1.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        Vector3f lightDirection = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(bounds);
        return light;
    }
}
