public class HighestIndex {

    public static void main(String[] args) {
        int[] arr = {1,2,1,3,5,6,4};
        int max = Integer.MIN_VALUE;
        int maxIndex =-1;

        for(int i=0;i<arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
                maxIndex = i;
            }
        }

        System.out.println(maxIndex);
    }
}
