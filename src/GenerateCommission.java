public class GenerateCommission {
	double setPrice;
	public double calculateCommission(String b, double sales) {
		
		if(sales<50000.0) { 
			setPrice= 0.0;}
		else if(sales>=50000.0 && sales<100000.0) {
			switch(b) {
			case "Main":
				setPrice= 10000.0;
				break;
			case "Nakawa":
				setPrice= 10500.0;
				break;
			case "Kawempe":
				setPrice= 9000.0;
				break;
			case "Mengo":
				setPrice= 8000.0;
				break;
			}
		}else if(sales>=100000.0 && sales<500000.0) {
			switch(b) {
			case "Main":
				setPrice= 12000.0;
				break;
			case "Nakawa":
				setPrice= 12800.0;
				break;
			case "Kawempe":
				setPrice= 11500.0;
				break;
			case "Mengo":
				setPrice= 10000.0;
				break;
			}
		}else if(sales>=500000.0 && sales<1000000.0) {
			switch(b) {
			case "Main":
				setPrice= 14000.0;
				break;
			case "Nakawa":
				setPrice= 15000.0;
				break;
			case "Kawempe":
				setPrice= 13000.0;
				break;
			case "Mengo":
				setPrice= 12500.0;
				break;
			}
		}else if(sales>=1000000.0 && sales<=1500000.0) {
			switch(b) {
			case "Main":
				setPrice= 18000.0;
				break;
			case "Nakawa":
				setPrice= 19000.0;
				break;
			case "Kawempe":
				setPrice= 17800.0;
				break;
			case "Mengo":
				setPrice= 16000.0;
				break;
			}
		}else if(sales > 1500000.0) {
			switch(b) {
			case "Main":
				setPrice= 0.15*sales;
				break;
			case "Nakawa":
				setPrice= 0.158*sales;
				break;
			case "Kawempe":
				setPrice= 0.145*sales;
				break;
			case "Mengo":
				setPrice= 0.134*sales;
				break;
			}
		}
		
		
		return setPrice;
		
	}
	
	

}

