public class Main {
    public static void main(String[] args) {
          int[] arr = {2,5,1,3,4,7,9,8};
          int n = 4;

          printPairs(arr,n);
    }

    private static void printPairs(int[] arr, int n){
        if(null == arr || arr.length ==0)
            throw new IllegalArgumentException("Array is empty");

        else if(arr.length == 1){
                System.out.println(arr);


        }
        else{
            int mid = n;
            int start = 0;
            int[] output = new int[arr.length];
            for(int i=0; i< arr.length; i++ ){
                if(i% 2 ==0){
                    output[i] = arr[start++];
                }
                else{
                    output[i] = arr[mid++];
                }
            }

           // System.out.println(output);
            for(int a: output)
                System.out.println(a);
        }
    }
}