public class Planet {

    //instance variance
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    //constructor
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass  = m;
        imgFileName = img;

    }
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;

    }
    public static final double gravitational_Constant =  6.67e-11;

    public double calcDistance(Planet a){
        double yDif = Math.abs(this.yyPos-a.yyPos);
        double xDif = Math.abs(this.xxPos-a.xxPos);
        double distance = Math.sqrt(yDif*yDif + xDif*xDif);

        return distance;
    }
    public double calcForceExertedBy(Planet a){
        double distance =  calcDistance(a);
        double f = gravitational_Constant*mass*a.mass/(distance*distance);
        return f;
    }

    public double calcForceExertedByX (Planet a){
        double xDif = a.xxPos-xxPos;
        double percent = xDif/calcDistance(a);
        return calcForceExertedBy(a)*percent;
    }
    public double calcForceExertedByY(Planet a){
        double yDif = a.yyPos-yyPos;
        double percent = yDif/calcDistance(a);
        return  calcForceExertedBy(a)*percent;

    }
    public double calcNetForceExertedByX(Planet[] bodyArray){
        double Netx = 0;
        for(Planet a:bodyArray){
            if(a.equals(this)){
                continue;
            }
           Netx += calcForceExertedByX(a);
        }
        return  Netx;
    }

    public double calcNetForceExertedByY(Planet[] bodyArray){
        double Nety = 0;
        for(Planet a:bodyArray){
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
