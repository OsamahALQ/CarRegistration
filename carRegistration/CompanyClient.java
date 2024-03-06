public class CompanyClient extends Client{

    public CompanyClient(String n){
    super(n);
}

public String toString(){
    return "Company"+super.toString();
}

public float makeClaim(int polNum){
    for (int i = 0 ; i < numPolicies ; i ++){
        if (policies[i].equals(getPolicy(polNum))){
            return policies[i].handleClaim();

        }
    }
    return 0;
}
}