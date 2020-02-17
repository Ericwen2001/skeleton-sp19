public class Body {
    //instance variance
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    //constructor
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass  = m;
        imgFileName = img;

    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;

    }
    public static final double gravitational_Constant =  6.67e-11;

    public double calcDistance(Body a){
        double yDif = Math.abs(this.yyPos-a.yyPos);
        double xDif = Math.abs(this.xxPos-a.xxPos);
        double distance = Math.sqrt(yDif*yDif + xDif*xDif);

        return distance;
    }
    public double calcForceExertedBy(Body a){
        double distance =  calcDistance(a);
        double f = gravitational_Constant*mass*a.mass/(distance*distance);
        return f;
    }

    public double calcForceExertedByX (Body a){
        double xDif = a.xxPos-xxPos;
        double percent = xDif/calcDistance(a);
        return calcForceExertedBy(a)*percent;
    }
    public double calcForceExertedByY(Body a){
        double yDif = a.yyPos-yyPos;
        double percent = yDif/calcDistance(a);
        return  calcForceExertedBy(a)*percent;

    }
    public double calcNetForceExertedByX(Body[] bodyArray){
        double Netx = 0;
        for(Body a:bodyArray){
            if(a.equals(this)){
                continue;
            }
           Netx += calcForceExertedByX(a);
        }
        return  Netx;
    }

    public double calcNetForceExertedByY(Body[] bodyArray){
        double Nety = 0;
        for(Body a:bodyArray){
            if(a.equals(this)){
                continue;
            }
            Nety += calcForceExertedByY(a);
        }
        return  Nety;
    }

    public void update(double second,double Fx ,double Fy){
        double accerlX = Fx/mass;
        double accerlY = Fy/mass;
        xxVel = xxVel + accerlX*second;
        yyVel = yyVel + accerlY*second;
        xxPos += second*xxVel;
        yyPos += second*yyVel;

    }

    public void draw(){
        String image = "./images/"+imgFileName;
        StdDraw.picture(xxPos,yyPos,image);
    }
}
