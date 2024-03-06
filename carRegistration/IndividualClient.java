import java.util.Date;

public class IndividualClient extends Client{


    int regularClaims = 0;
    public IndividualClient(String n){
    super(n);
}

public String toString(){
    return "Individual"+super.toString();
}

public float makeClaim(int polNum){
    float temp;
    for (int i = 0 ; i < numPolicies ; i ++){
        if (policies[i].equals(getPolicy(polNum))){
            if(policies[i].getClass().getName().equals("DepreciatingPolicy")){
                ((DepreciatingPolicy)policies[i]).depreciate();
                return policies[i].getAmount();
            }else if (policies[i].getClass().getName().equals("ExpiringPolicy")){
                if(((ExpiringPolicy)policies[i]).isExpired()){
                    return 0;
                }
                return policies[i].getAmount();
            
            }else if (policies[i].getClass().getName().equals("Policy") && regularClaims < 1){
                regularClaims++;
                temp = policies[i].getAmount();
                cancelPolicy(polNum);
                return temp;
            }else if (policies[i].getClass().getName().equals("Policy")){
                    cancelPolicy(polNum);
                    return 0;
            }
    }


        }
    return 0;
        }
}