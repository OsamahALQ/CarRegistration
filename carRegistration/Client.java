import java.util.Date;
import java.util.regex.PatternSyntaxException;

public abstract class Client {
    private static final int MAX_POLICIES_PER_CLIENT = 10;

    private static int NEXT_CLIENT_ID = 1;
    private   String        name;
    private   int           id;
    protected Policy[]      policies;
    protected int           numPolicies;

    public Client(String n) {
        name = n;
        id = NEXT_CLIENT_ID++;
        policies = new Policy[MAX_POLICIES_PER_CLIENT];
        numPolicies = 0;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public Policy[] getPolicies() { return policies; }
    public int getNumPolicies() { return numPolicies; }

    public String toString() {
        return String.format("Client: %06d amount: %s", id, name);
    }

    public float totalCoverage(){
        float total = 0;

        for (int i = 0 ; i < numPolicies; i++){
            total = total + policies[i].getAmount();
        }

        return total;
    }

    public Policy addPolicy(Policy p){
        if (numPolicies < policies.length){
            policies[numPolicies++]=p;
        }
        return p;
    }

    public Policy openPolicyFor(float amt){

        Policy newPolicy = new Policy(amt);
        return addPolicy(newPolicy);
    }

    public Policy openPolicyFor(float amt,float rate){
        DepreciatingPolicy newDepreciatingPolicy = new DepreciatingPolicy(amt, rate);
    

    return addPolicy(newDepreciatingPolicy);
    }
    public Policy openPolicyFor(float amt,Date expire){
        ExpiringPolicy newExpiringPolicy = new ExpiringPolicy(amt, expire);

        return addPolicy(newExpiringPolicy);
    }


    public Policy getPolicy(int polNum){
        for (int i = 0 ; i < numPolicies ; i++){
            if (polNum == policies[i].getPolicyNumber()){
                return policies[i];
            }
    }
    return null;
    }   

    public boolean cancelPolicy(int polNum){
        for (int i = 0 ; i < numPolicies ; i ++){
            if (policies[i].equals(getPolicy(polNum))){
                policies[i] = policies[numPolicies-1];
                policies[numPolicies-1]=null;
                numPolicies--;
                return true;
            }


        }
        return false;
    }
    public abstract float makeClaim(int polNum);
}
