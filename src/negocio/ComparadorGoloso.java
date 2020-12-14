package negocio;

import java.util.Comparator;

public class ComparadorGoloso implements Comparator<Object> {
	
	    public int compare(Object obj1, Object obj2) {
	    	Centro myObj1 = (Centro)obj1;
	    	Centro myObj2 = (Centro)obj2;
	        int compResult = Integer.compare(myObj1.getClientesAsociados().size(), myObj2.getClientesAsociados().size());
	        if (compResult == 0) {
	            // Strings are equal, sort by date
	            return Double.compare(myObj1.getUtilidad(), myObj2.getUtilidad());
	        }
	        else {
	            return compResult;
	        }
	    }

}
