package Lab6;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.io.FileReader;

public class App extends JFrame{
    SimpleUniverse universe;
    Scene scene;
    Canvas3D canvas;

    BranchGroup root;
    Transform3D catTransform = new Transform3D();
    Vector3d euler = new Vector3d(0.0, 0.0, 0.0);

    TransformGroup cosmos;
    TransformGroup cat;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.setVisible(true);
    }

    public App() throws Exception {
        setupUniverse();
        setupLight();
        loadModel();
        animate();
        addImageBackground();

        root.compile();
        universe.addBranchGraph(root);
    }
    private void addImageBackground(){
        TextureLoader t = new TextureLoader("cosmos.jpg", canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }
    private void setupUniverse() {
        setTitle("Lab5");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        getContentPane().add(canvas);

        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();

        root = new BranchGroup();
    }

    private void setupLight() {
        Bounds bounds = new BoundingSphere(new Point3d(0, 0, 0), 10);
        Color3f color = new Color3f(1f, 1f, 1f);
        Vector3f dir = new Vector3f(-0.5f, -1f, -1f);
        DirectionalLight light = new DirectionalLight(color, dir);
        light.setInfluencingBounds(bounds);
        root.addChild(light);
    }

    private void loadModel() throws Exception {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);

        Scene catScene = file.load(new FileReader("nyan_cat.obj"));
        addTextureAppearance((Shape3D)catScene.getNamedObjects().get("cat"), getTexture("nyan_cat.png"), new Material());

        cat = new TransformGroup();
        cat.addChild(((Shape3D)catScene.getNamedObjects().get("cat")).cloneTree());

        //Cylinder cosmos = new Cylinder(5.0f, 5.0f);
        //addTextureAppearance(cosmos.getShape(0), getTexture("сosmos.jpg"), new Material());


        TransformGroup all = new TransformGroup();

        Transform3D tf = new Transform3D();
        tf.setScale(0.2);
        cat.setTransform(tf);
        tf.setEuler(new Vector3d(0,Math.PI/12,0));
        all.setTransform(tf);
        all.addChild(cat);
        //root.addChild(applyTransform(cosmos,null));
        root.addChild(all);
    }

    Texture getTexture(String name) {
        TextureLoader textureLoader = new TextureLoader(name, "LUMINANCE", canvas);
        Texture texture = textureLoader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );
        texture.setAnisotropicFilterMode(Texture.ANISOTROPIC_SINGLE_VALUE);
        texture.setAnisotropicFilterDegree(15.0f);
        return texture;
    }
    private static String getResourcePath(String resource) {
        return resource;
    }
    private static TransformGroup applyTransform(Node object, Transform3D tf) {
        TransformGroup group = new TransformGroup();
        group.setTransform(tf);
        group.addChild(object);
        return group;
    }

    private void addTextureAppearance(Shape3D shape, Texture texture, Material material){
        Appearance app = new Appearance();
        app.setTexture(texture);
        app.setMaterial(material);

        TextureAttributes attr = new TextureAttributes();
        attr.setTextureMode(TextureAttributes.REPLACE);
        app.setTextureAttributes(attr);

        shape.setAppearance(app);
    }

    private void animate() {
        if (cat != null) {
            euler.setZ(euler.getZ() + 0.01);
            euler.setX(euler.getX() + 0.01);
            catTransform.setEuler(euler);
            catTransform.setScale(0.25);
            cat.setTransform(catTransform);
        }

    }
}
