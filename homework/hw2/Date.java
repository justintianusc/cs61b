package hw2;


public class Date {
	private int day;
	private int month;
	private int year;
	
	Date(int month,int day,int year){	
		if(isValidDate(month,day,year)==false){
			System.out.println("Oops, invalid date!");
			System.exit(0);
		}
		this.day=day;
		this.month=month;
		this.year=year;
	}

	Date(String s){
		String[] parts=s.split("/");
		String part1=parts[0];
		String part2=parts[1];
		String part3=parts[2];
		int m=Integer.parseInt(part1);
		int d=Integer.parseInt(part2);
		int y=Integer.parseInt(part3);
		if(isValidDate(m,d,y)==false){
			System.out.println("Oops, invalid date!");
			System.exit(0);
		}else{
			this.month=m;
			this.day=d;
			this.year=y;
		}
	}
	
	public String toString(){
		String str=this.month+"/"+this.day+"/"+this.year;
		return str;
	}
	
	public boolean isBefore(Date d){
		boolean res;
		if(this.year>d.year){
			res=false;
		}else if(this.year<d.year){
			res=true;
		}else{
			if(this.month>d.month){
				res=false;
			}else if(this.month<d.month){
				res=true;
			}else{
				if(this.day<d.day){
					res=true;
				}else{
					res=false;
				}
			}
		}
		return res;
	}
	
	public boolean isAfter(Date d){
		boolean res;
		if(this.isBefore(d)==true||(this.year==d.year&&this.month==d.month&&this.day==d.day)){
			res=false;
		}else{
			res=true;
		}
		return res;
	}
	
	public int dayInYear(){
		int num=0;
		 for(int currentMon=1;currentMon<this.month;currentMon++){
			num+=daysInMonth(currentMon,this.year);			
		   }
		 num+=this.day;
		return num;
		
	}
	
	private static int daysInAYear(int year){
		int result;
		Date lastDate=new Date(12,31,year);
		result=lastDate.dayInYear();
		return result;
	}
	
	public int difference(Date d){
		int diff=0;
		if(this.year==d.year){
			diff=this.dayInYear()-d.dayInYear();
			return diff;
		}else if(this.isAfter(d)){
		    Date d1=new Date(12,31,d.year);
			int d_toEndOfDec=d1.difference(d);
			diff+=d_toEndOfDec;
			for(int gap=d.year+1;gap<this.year;gap++){
				diff+=daysInAYear(gap);
			}
			diff+=this.dayInYear();
			return diff;
		}else{
			Date d2=new Date(12,31,this.year);
			Date d3=new Date(1,1,d.year);
			int this_toEndOfDec=d2.difference(this);
			int d_toBeginOfJan=d.difference(d3);
			diff-=this_toEndOfDec;
			for(int gap=d.year-1;gap>this.year;gap--){
				diff-=daysInAYear(gap);
			}
			diff-=d_toBeginOfJan+1;
			return diff;
		}
		
	}
	
	public static void main(String[] argv) {
			    System.out.println("\nTesting constructors.");
			    Date d1 = new Date(1, 1, 1);
			    System.out.println("Date should be 1/1/1: " + d1);
			    d1 = new Date("2/4/2");
			    System.out.println("Date should be 2/4/2: " + d1);
			    d1 = new Date("2/29/2000");
			    System.out.println("Date should be 2/29/2000: " + d1);
			    d1 = new Date("2/29/1904");
			    System.out.println("Date should be 2/29/1904: " + d1);

			    d1 = new Date(12, 31, 1975);
			    System.out.println("Date should be 12/31/1975: " + d1);
			    Date d2 = new Date("1/1/1976");
			    System.out.println("Date should be 1/1/1976: " + d2);
			    Date d3 = new Date("1/2/1976");
			    System.out.println("Date should be 1/2/1976: " + d3);

			    Date d4 = new Date("2/27/1977");
			    Date d5 = new Date("8/31/2110");
			    int y1=2014;
			    int y2=1900;
			    int y3=1600;
			    int y4=2012;

			    /* I recommend you write code to test the isLeapYear function! */
			    System.out.println("\nTesting isLeapYear");
			    System.out.println("That "+y1+" is leapyear should be false: "+isLeapYear(y1));
			    System.out.println("That "+y2+" is leapyear should be false: "+isLeapYear(y2));
			    System.out.println("That "+y3+" is leapyear should be true: "+isLeapYear(y3));
			    System.out.println("That "+y4+" is leapyear should be true: "+isLeapYear(y4));

			    System.out.println("\nTesting before and after.");
			    System.out.println(d2 + " after " + d1 + " should be true: " + 
			                       d2.isAfter(d1));
			    System.out.println(d3 + " after " + d2 + " should be true: " + 
			                       d3.isAfter(d2));
			    System.out.println(d1 + " after " + d1 + " should be false: " + 
			                       d1.isAfter(d1));
			    System.out.println(d1 + " after " + d2 + " should be false: " + 
			                       d1.isAfter(d2));
			    System.out.println(d2 + " after " + d3 + " should be false: " + 
			                       d2.isAfter(d3));

			    System.out.println(d1 + " before " + d2 + " should be true: " + 
			                       d1.isBefore(d2));
			    System.out.println(d2 + " before " + d3 + " should be true: " + 
			                       d2.isBefore(d3));
			    System.out.println(d1 + " before " + d1 + " should be false: " + 
			                       d1.isBefore(d1));
			    System.out.println(d2 + " before " + d1 + " should be false: " + 
			                       d2.isBefore(d1));
			    System.out.println(d3 + " before " + d2 + " should be false: " + 
			                       d3.isBefore(d2));

			    System.out.println("\nTesting difference.");
			    System.out.println(d1 + " - " + d1  + " should be 0: " + 
			                       d1.difference(d1));
			    System.out.println(d2 + " - " + d1  + " should be 1: " + 
			                       d2.difference(d1));
			    System.out.println(d3 + " - " + d1  + " should be 2: " + 
			                       d3.difference(d1));
			    System.out.println(d3 + " - " + d4  + " should be -422: " + 
			                       d3.difference(d4));
			    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
			                       d5.difference(d4));
			    
			  }	
		
	/* this is the "boolean isLeapYear(int year)" method*/
	public static boolean isLeapYear(int year){
		if(year%4==0){
			if(year%100==0){
				if(year%400==0){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}else{
			return false;
		}	
	}
	
	/*this is the "int daysInMonth(int month,int year)" method */
	public static int daysInMonth(int month, int year){
		int days=0;
		
		switch(month){
		case 1: days=31;
				break;
		case 2: if(isLeapYear(year)){
					days=29;
				}else{
					days=28;
				}
				break;
		case 3: days=31;
				break;
		case 4: days=30;
				break;
		case 5: days=31;
				break;
		case 6: days=30;
				break;
		case 7: days=31;
				break;
		case 8: days=31;
				break;
		case 9: days=30;
				break;
		case 10: days=31;
				 break;
		case 11: days=30;
				 break;
		case 12: days=31;
				 break;
		default: break;		
		}	
		return days;		
	}
	
	/* this is the "boolean isValidDate(int month, int dat, int year)" method. */
	public static boolean isValidDate(int month, int day, int year){
		boolean res=false;
		switch(month){
		case 1: 
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: if(day<1||day>31){
			          res=false;
		         }else{
		        	 res=true;
		         }
				 break;
		case 2: if(isLeapYear(year)){
			         if(day<1||day>29){
			        	 res=false;
			         }else{
			        	 res=true;
			         }
				}else{
					if(day<1||day>28){
			        	 res=false;
			         }else{
			        	 res=true;
			         }
				}
				break;
		case 4: 
		case 6:
		case 9:
		case 11:  if(day<1||day>30){
					res=false;
				  }else{
					res=true;  
				  }
				  break;
		default:break;
		}
		return res;
	}
		
		
	
}
