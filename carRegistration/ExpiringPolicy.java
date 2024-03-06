import javax.management.loading.PrivateClassLoader;

import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ExpiringPolicy extends Policy{


private Date expiryDate;



public ExpiringPolicy(float amt){
    super(amt);
    GregorianCalendar aCalendar = new GregorianCalendar();
    aCalendar.add(Calendar.YEAR,1);
    expiryDate = aCalendar.getTime();
}
public ExpiringPolicy(float amt,Date expiryDate){
    super(amt);
    this.expiryDate=expiryDate;
}

public Date getExpiryDate(){
    return expiryDate;
}

public String toString(){
    Date current = new Date();
    if (expiryDate.after(current)){
        return "Expiring"+super.toString()+" expires: "+(new SimpleDateFormat("MMMM dd, yyyy (hh:mma)").format(getExpiryDate()));
    }else{
        return "Expiring"+super.toString()+" expired on: "+(new SimpleDateFormat("MMMM dd, yyyy (hh:mma)").format(getExpiryDate()));
 
    }
}

public boolean isExpired(){
    Date current = new Date();
    return (expiryDate == current || current.after(expiryDate));
}

public float handleClaim(){
    if (!isExpired()){
        return amount;
    }else{return 0;}
}
}

