import javax.management.loading.PrivateClassLoader;

public class DepreciatingPolicy extends Policy{

private float rate;

public DepreciatingPolicy(float amount, float rate){
    super(amount);
    this.rate = rate;

}

public float getRate(){
    return rate;
}
@Override
public String toString(){

    return "Depreciating"+super.toString()+" rate: "+this.rate*100+"%";
}

public boolean isExpired(){

    return (amount <= 0);
}

public void depreciate(){
    super.amount = super.amount - (super.amount*this.rate);

}

public float handleClaim(){
    float temp = amount;
    depreciate();
    return temp;
}
}