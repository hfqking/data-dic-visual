public class Test {
    public static void main(String[] args) throws InterruptedException {
        int size = 60000;
//        if(size>65000){
            int num = size /65000;
            System.out.println(num);
//            if(num*65000>size)
            for (int j = 0; j < num+1; j++) {
                if(j<num){
                    for (int k = j*65000; k <(j+1)*65000 ; k++) {
                        System.out.println(k);
                    }
//                    System.out.println(j+">"+(j+1)*65000);
                    Thread.sleep(3000);
                }else{
//                    System.out.println(j*65000+"===>"+size);
                    for (int k = j*65000; k <size ; k++) {
                        System.out.println(k);
                    }
                }
            }
//        }
    }
}
