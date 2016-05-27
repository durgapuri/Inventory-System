package LoginPackage;


public class EncDecrptClass {
    
    private static final int[] array={
                2226,7865,45678,23338,9900,7789,5678,57909,45678,44322,
                
   };
    
     public static String encrypt(String key){
            
            
            String result="";
            int l=key.length();
            char ch;
            int ck=0;
            for(int i=0;i<l;i++){
                if(ck>=array.length-1)
                    ck=0;
                ch=key.charAt(i);
                ch+=array[ck];
                result+=ch;
                
    
}
            return result;
}
     public static String decrypt(String key){
         
            String result="";
            int l=key.length();
            char ch;
            int ck=0;
            for(int i=0;i<l;i++){
                if(ck>=array.length-1)
                    ck=0;
                ch=key.charAt(i);
                ch-=array[ck];
                result+=ch;
                ch++;
                System.out.println(result);
                
    
}
            return result;
         
     }
   
    
}
