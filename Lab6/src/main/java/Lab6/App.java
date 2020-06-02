package Lab6;

        import com.sun.j3d.utils.universe.*;

        import javax.media.j3d.*;
        import javax.media.j3d.Material;
        import javax.vecmath.*;

        import com.sun.j3d.loaders.*;
        import com.sun.j3d.loaders.objectfile.ObjectFile;
        import java.io.FileReader;
        import javax.swing.JFrame;


public class App extends JFrame {
    SimpleUniverse universe;
    Scene scene;
    Canvas3D canvas;

    BranchGroup root;

    TransformGroup head;
    TransformGroup hands;
    TransformGroup axe;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.setVisible(true);
    }

    public App() throws Exception {
        setupUniverse();
        setupLight();
        loadModel();
        animate();

        root.compile();
        universe.addBranchGraph(root);
    }

    private void setupUniverse() {
        setTitle("Lab6");
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

        Scene axeScene = file.load(new FileReader("axe.obj"));
        Scene headScene = file.load(new FileReader("avatarian.obj"));
        Scene handsScene = file.load(new FileReader("handObj.obj"));
        for (Object key : axeScene.getNamedObjects().keySet()) {
            Shape3D el = (Shape3D)axeScene.getNamedObjects().get(key);
            Appearance app = el.getAppearance();
            Material mtl = app.getMaterial();
            mtl.setDiffuseColor(1.0f, 0.35f, 0.0f);
            app.setMaterial(mtl);
            el.setAppearance(app);
        }
        for (Object key : headScene.getNamedObjects().keySet()) {
            Shape3D el = (Shape3D)headScene.getNamedObjects().get(key);
            Appearance app = el.getAppearance();
            Material mtl = app.getMaterial();
            mtl.setDiffuseColor(0.35f, 1.0f, 0.0f);
            app.setMaterial(mtl);
            el.setAppearance(app);
        }
        for (Object key : handsScene.getNamedObjects().keySet()) {
            Shape3D el = (Shape3D)handsScene.getNamedObjects().get(key);
            Appearance app = el.getAppearance();
            Material mtl = app.getMaterial();
            mtl.setDiffuseColor(0.35f, 1.0f, 0.0f);
            app.setMaterial(mtl);
            el.setAppearance(app);
        }



        head = new TransformGroup();
        head.addChild(((Shape3D)headScene.getNamedObjects().get("head")).cloneTree());

        hands = new TransformGroup();
        hands.addChild(((Shape3D)handsScene.getNamedObjects().get("hand")).cloneTree());

        axe = new TransformGroup();
        axe.addChild(((Shape3D)axeScene.getNamedObjects().get("axe")).cloneTree());




        Transform3D tfAxeHand = new Transform3D();
        tfAxeHand.setTranslation(new Vector3d(0.5,0.5,0.0));
        axe.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        axe.setTransform(tfAxeHand);
        hands.addChild(axe);
        tfAxeHand.setTranslation(new Vector3d(5.0,5.0,5.0));
        hands.setTransform(tfAxeHand);
        head.addChild(hands);


        TransformGroup warrior = new TransformGroup();


        Transform3D tf = new Transform3D();
        tf.setScale(0.2);

        warrior.setTransform(tf);
        warrior.addChild(head);



        root.addChild(warrior);
    }

    private void animate() {
        float angle = (float)Math.PI/8;
        float angle1 = (float)Math.PI/16;
        float angle2 = (float)Math.PI*2;
        int duration = 750;

        Transform3D axis = new Transform3D();
        axis.rotZ(Math.PI/2);
        Alpha a = new Alpha(-1, duration);
        a.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
        a.setDecreasingAlphaDuration(duration);
        RotationInterpolator rotation = new RotationInterpolator(a, hands, axis, -angle, angle);

        Bounds bounds = new BoundingSphere(new Point3d(), 100);
        rotation.setSchedulingBounds(bounds);

        hands.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        hands.addChild(rotation);

        axis.rotX(Math.PI/2);
        a = new Alpha(-1, duration);
        a.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
        a.setDecreasingAlphaDuration(duration);
        a.setPhaseDelayDuration(duration);
        rotation = new RotationInterpolator(a, head, axis, -angle1, angle1);
        rotation.setSchedulingBounds(bounds);

        head.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        head.addChild(rotation);

        axis.rotX(Math.PI*2);
        a = new Alpha(-1, duration);
        a.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
        a.setDecreasingAlphaDuration(duration);
        a.setPhaseDelayDuration(duration);
        rotation = new RotationInterpolator(a, axe, axis, 0, angle2);
        rotation.setSchedulingBounds(bounds);

        axe.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        axe.addChild(rotation);

    }
}
