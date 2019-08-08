package studyserverone.single;


import studyserverone.single.single1.Single;

/**
 * @author zhutong
 * @date 2019/5/16 20:39
 */
public class Main {
    
    public static void main(String[] args) {
        //1
        System.out.println(Single.getSingle()  == Single.getSingle());
        //2
        System.out.println(studyserverone.single.single2.Single.getSingle() == studyserverone.single.single2.Single.getSingle());
        //3
        System.out.println(studyserverone.single.single3.Single.getSingle() == studyserverone.single.single3.Single.getSingle());
        //4
        System.out.println(studyserverone.single.single4.Single.getSingle() == studyserverone.single.single4.Single.getSingle());
        //5
        System.out.println(studyserverone.single.single5.Single.getSingle() == studyserverone.single.single5.Single.getSingle());
        //6
        System.out.println(studyserverone.single.single6.Single.getSingle() == studyserverone.single.single6.Single.getSingle());
        //7
        System.out.println(studyserverone.single.single7.Single.getSingle() == studyserverone.single.single7.Single.getSingle());
        //8
        System.out.println(studyserverone.single.single8.Single.INSTANCE == studyserverone.single.single8.Single.INSTANCE);
        
    }
}
