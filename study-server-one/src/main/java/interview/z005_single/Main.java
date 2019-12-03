package interview.z005_single;


import java.text.SimpleDateFormat;

/**
 * @author zhutong
 * @date 2019/5/16 20:39
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
//        //1
//        System.out.println(Single.getSingle()  == Single.getSingle());
//        //2
//        System.out.println(studyserverone.z005_single.single2.Single.getSingle() == studyserverone.z005_single.single2.Single.getSingle());
//        //3
//        System.out.println(studyserverone.z005_single.single3.Single.getSingle() == studyserverone.z005_single.single3.Single.getSingle());
//        //4
//        System.out.println(studyserverone.z005_single.single4.Single.getSingle() == studyserverone.z005_single.single4.Single.getSingle());
//        //5
//        System.out.println(studyserverone.z005_single.single5.Single.getSingle() == studyserverone.z005_single.single5.Single.getSingle());
//        //6
//        System.out.println(studyserverone.z005_single.single6.Single.getSingle() == studyserverone.z005_single.single6.Single.getSingle());
//        //7
//        System.out.println(studyserverone.z005_single.single7.Single.getSingle() == studyserverone.z005_single.single7.Single.getSingle());
//        //8
//        System.out.println(studyserverone.z005_single.single8.Single.INSTANCE == studyserverone.z005_single.single8.Single.INSTANCE);
    
        String str = "2019/08/8";
        str = str.replace("-","/");
        String regex = "^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}";
        System.out.println(str.matches(regex));
    
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(format.parse(str));
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//        Date pro = format.parse("s");
        
    }
}
