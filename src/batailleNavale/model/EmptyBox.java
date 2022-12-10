package batailleNavale.model;


public class EmptyBox extends Box implements java.io.Serializable {
    private static final long serialVersionUID = -8575937733681669132L;
    
    /**
     *
     * @return "~" if the box is empty
     */
    public String toString(){
        return "~";
    }
    
}
