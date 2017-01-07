
public class Point {
	public double x;
	public double y;
	public double slope;
	public double angle;
	public Point(double x, double y, double slope, double angle){
		this.x = x;
		this.y = y;
		this.slope=slope;
		this.angle=angle;
	}
	
	public void increment(){
		if(angle<90){
			x+=10;
			y+=slope*10;
		}
		else if(angle==90){
			y-=8;
		}
		else{
			x-=10;
			y-=slope*10;
		}
		
	}
	public String toString(){
		return x +" , " +y;
	}
}
