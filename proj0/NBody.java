import java.util.Arrays;

public class NBody {
    public static double readRadius(String a) {
        In in = new In(a);
        int planetNumber = in.readInt();
        double radius = in.readDouble();
        return radius;

    }

    public static Planet[] readPlanets(String a) {
        In in = new In(a);
        int planetNumber = in.readInt();
        double radius = in.readDouble();
        Planet[] bodies = new Planet[planetNumber];
        for (int i = 0; i < planetNumber; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            bodies[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] bodies = readPlanets(filename);
        StdDraw.setScale(-radius, radius);

        String background = "./images/starfield.jpg";
        StdDraw.picture(0, 0, background);
        for (Planet a : bodies) {
            a.draw();
        }

        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time < T) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);

            }
            for(int i=0;i<bodies.length;i++){
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, background);
            for (Planet a : bodies) {
                a.draw();
            }
            StdDraw.show();
            StdDraw.pause(1);
            time+=dt;
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : bodies) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }


    }


}
