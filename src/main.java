import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class main {

    public static void main(String args[]) throws FileNotFoundException {
        // TASK 1
        // TASK 1
        // TASK 1

        // file paths are put into variables

        String bustopher = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/bustopher-jones-the-cat-about-town.txt";
        String growltigers = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/growltigers-last-stand.txt";
        String gus = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/gus-the-theater-cat.txt";
        String macavity = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/macavity-the-mystery-cat.txt";
        String mistoffelees = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/mr-mistoffelees.txt";
        String mungojerrie = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/mungojerrie-and-rumpelteazer.txt";
        String awefull = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/of-the-awefull-battle-of-the-pekes-and-the-pollicles.txt";
        String deuteronomy = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/old-deuteronomy.txt";
        String skimbleshanks = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/skimbleshanks-the-railway-cat.txt";
        String ad = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/the-ad-dressing-of-cats.txt";
        String naming = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/the-naming-of-cats.txt";
        String gumbie = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/the-old-gumbie-cat.txt";
        String rum = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/the-rum-tum-tugger.txt";
        String jellicles = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/the-song-of-the-jellicles.txt";
        String massive = "/Users/nupurshukla/intellij-workspace/Q1 - HW 3/src/massive.txt";


        readFile r15 = new readFile();                          // an instance of the readFile class is made
        String massiveWords = r15.readFileMethod(massive);      // String containing file path is passed to the readFile method
                                                                // which places all words into a String array

        String massive_array[] = massiveWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+"); // punctuation is taken out and all letters are lowercase

        LinearProbingHashST<String, Term> massive_hash = new LinearProbingHashST<String, Term>(); // hashtable is created

        // loop through each str in the String array
        for(String str : massive_array) {
            Term temp = (Term) massive_hash.get(str);  // create a temporary object of class Term
            if (temp != null) {                        // check if temp is null
                Integer i = temp.getFrequency();       // if not, get the freq, add 1 to it and store all necessary values in hashtable
                massive_hash.put(str, new Term(str, "massive", i + 1, 0));
            }
            else {                                     // if temp is null, set freq to 1 and store all necessary values in hashtable
                massive_hash.put(str, new Term(str, "massive", 1, 0));
            }
        }

        // repeat process for another file hashtable
        readFile r1 = new readFile();
        String bustopherWords = r1.readFileMethod(bustopher);

        String bustopher_array[] = bustopherWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");

        //Stopwatch constructionHashStopwatch = new Stopwatch();
        long startHashConstructionTime = System.currentTimeMillis();   // start stopwatch for hash construction

        LinearProbingHashST<String, Term> bustopher_hash = new LinearProbingHashST<String, Term>();
        for(String str : bustopher_array) {
            Term temp = (Term) bustopher_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                bustopher_hash.put(str, new Term(str, "bustopher", i + 1, 0));
            }
            else {
                bustopher_hash.put(str, new Term(str, "bustopher", 1, 0));
            }
        }

        // instance of Calculation class is created
        Calculations calc1 = new Calculations();
        int bustopher_totalWords = bustopher_array.length;      // total words is the length of the String array which holds all words of the file

        // for each String in given hash table
        for (String str : bustopher_hash.keys()) {
            float tf = calc1.calculateTF(bustopher_hash, bustopher_totalWords, str); // use tf method to calc tf
            float idf = calc1.calculateIDF(massive_hash, bustopher_hash, str);       // use idf method to calc idf
            float tfidf = calc1.calculateTF_IDF(tf, idf);                           // use tfidf method to calc tf_idf
            bustopher_hash.get(str).setTd_Idf(tfidf);                               // for each str, set the tf_idf to calculated tf_idf
        }


        // repeat process for another file hashtable
        readFile r2 = new readFile();
        String growltigersWords = r2.readFileMethod(growltigers);

        String growltigers_array[] = growltigersWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> growltigers_hash = new LinearProbingHashST<String, Term>();
        for(String str : growltigers_array) {
            Term temp = (Term) growltigers_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                growltigers_hash.put(str, new Term(str, "growltigers", i + 1, 0));
            }
            else {
                growltigers_hash.put(str, new Term(str, "growltigers", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc2 = new Calculations();
        int growltigers_totalWords = growltigers_array.length;

        for (String str : growltigers_hash.keys()) {
            float tf = calc2.calculateTF(growltigers_hash, growltigers_totalWords, str);
            float idf = calc2.calculateIDF(massive_hash, growltigers_hash, str);
            float tfidf = calc2.calculateTF_IDF(tf, idf);
            growltigers_hash.get(str).setTd_Idf(tfidf);
        }


        // repeat process for another file hashtable
        readFile r3 = new readFile();
        String gusWords = r3.readFileMethod(gus);

        String gus_array[] = gusWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");

        LinearProbingHashST<String, Term> gus_hash = new LinearProbingHashST<String, Term>();
        for(String str : gus_array) {
            Term temp = (Term) gus_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                gus_hash.put(str, new Term(str, "gus", i + 1, 0));
            }
            else {
                gus_hash.put(str, new Term(str, "gus", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc3 = new Calculations();
        int gus_totalWords = gus_array.length;

        for (String str : gus_hash.keys()) {
            float tf = calc3.calculateTF(gus_hash, gus_totalWords, str);
            float idf = calc3.calculateIDF(massive_hash, gus_hash, str);
            float tfidf = calc3.calculateTF_IDF(tf, idf);
            gus_hash.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file hashtable
        readFile r4 = new readFile();
        String macavityWords = r4.readFileMethod(macavity);

        String macavity_array[] = macavityWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> macavity_hash = new LinearProbingHashST<String, Term>();
        for(String str : macavity_array) {
            Term temp = (Term) macavity_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                macavity_hash.put(str, new Term(str, "macavity", i + 1, 0));
            }
            else {
                macavity_hash.put(str, new Term(str, "macavity", 1, 0));
            }
        }


        // repeat calculations
        Calculations calc4 = new Calculations();
        int macavity_totalWords = macavity_array.length;

        for (String str : macavity_hash.keys()) {
            float tf = calc4.calculateTF(macavity_hash, macavity_totalWords, str);
            float idf = calc4.calculateIDF(massive_hash, macavity_hash, str);
            float tfidf = calc4.calculateTF_IDF(tf, idf);
            macavity_hash.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file hashtable
        readFile r5 = new readFile();
        String mistoffeleesWords = r5.readFileMethod(mistoffelees);

        String mistoffelees_array[] = mistoffeleesWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> mistoffelees_hash = new LinearProbingHashST<String, Term>();
        for(String str : mistoffelees_array) {
            Term temp = (Term) mistoffelees_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                mistoffelees_hash.put(str, new Term(str, "mistoffelees", i + 1, 0));
            }
            else {
                mistoffelees_hash.put(str, new Term(str, "mistoffelees", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc5 = new Calculations();
        int mistoffelees_totalWords = mistoffelees_array.length;

        for (String str : mistoffelees_hash.keys()) {
            float tf = calc5.calculateTF(mistoffelees_hash, mistoffelees_totalWords, str);
            float idf = calc5.calculateIDF(massive_hash, mistoffelees_hash, str);
            float tfidf = calc5.calculateTF_IDF(tf, idf);
            mistoffelees_hash.get(str).setTd_Idf(tfidf);
        }




        // repeat process for another file hashtable
        readFile r6 = new readFile();
        String mungojerrieWords = r6.readFileMethod(mungojerrie);

        String mungojerrie_array[] = mungojerrieWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> mungojerrie_hash = new LinearProbingHashST<String, Term>();
        for(String str : mungojerrie_array) {
            Term temp = (Term) mungojerrie_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                mungojerrie_hash.put(str, new Term(str, "mungojerrie", i + 1, 0));
            }
            else {
                mungojerrie_hash.put(str, new Term(str, "mungojerrie", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc6 = new Calculations();
        int mungojerrie_totalWords = mungojerrie_array.length;

        for (String str : mungojerrie_hash.keys()) {
            float tf = calc6.calculateTF(mungojerrie_hash, mungojerrie_totalWords, str);
            float idf = calc6.calculateIDF(massive_hash, mungojerrie_hash, str);
            float tfidf = calc6.calculateTF_IDF(tf, idf);
            mungojerrie_hash.get(str).setTd_Idf(tfidf);
        }




        // repeat process for another file hashtable
        readFile r7 = new readFile();
        String awefullWords = r7.readFileMethod(awefull);

        String awefull_array[] = awefullWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> awefull_hash = new LinearProbingHashST<String, Term>();
        for(String str : awefull_array) {
            Term temp = (Term) awefull_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                awefull_hash.put(str, new Term(str, "awefull", i + 1, 0));
            }
            else {
                awefull_hash.put(str, new Term(str, "awefull", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc7 = new Calculations();
        int awefull_totalWords = awefull_array.length;

        for (String str : awefull_hash.keys()) {
            float tf = calc7.calculateTF(awefull_hash, awefull_totalWords, str);
            float idf = calc7.calculateIDF(massive_hash, awefull_hash, str);
            float tfidf = calc7.calculateTF_IDF(tf, idf);
            awefull_hash.get(str).setTd_Idf(tfidf);
        }




        // repeat process for another file hashtable
        readFile r8 = new readFile();
        String deuteronomyWords = r8.readFileMethod(deuteronomy);

        String deuteronomy_array[] = deuteronomyWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> deuteronomy_hash = new LinearProbingHashST<String, Term>();
        for(String str : deuteronomy_array) {
            Term temp = (Term) deuteronomy_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                deuteronomy_hash.put(str, new Term(str, "deuteronomy", i + 1, 0));
            }
            else {
                deuteronomy_hash.put(str, new Term(str, "deuteronomy", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc8 = new Calculations();
        int deuteronomy_totalWords = deuteronomy_array.length;

        for (String str : deuteronomy_hash.keys()) {
            float tf = calc8.calculateTF(deuteronomy_hash, deuteronomy_totalWords, str);
            float idf = calc8.calculateIDF(massive_hash, deuteronomy_hash, str);
            float tfidf = calc8.calculateTF_IDF(tf, idf);
            deuteronomy_hash.get(str).setTd_Idf(tfidf);
        }





        // repeat process for another file hashtable
        readFile r9 = new readFile();
        String skimbleshanksWords = r9.readFileMethod(skimbleshanks);

        String skimbleshanks_array[] = skimbleshanksWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> skimbleshanks_hash = new LinearProbingHashST<String, Term>();
        for(String str : skimbleshanks_array) {
            Term temp = (Term) skimbleshanks_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                skimbleshanks_hash.put(str, new Term(str, "skimbleshanks", i + 1, 0));
            }
            else {
                skimbleshanks_hash.put(str, new Term(str, "skimbleshanks", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc9 = new Calculations();
        int skimbleshanks_totalWords = skimbleshanks_array.length;

        for (String str : skimbleshanks_hash.keys()) {
            float tf = calc9.calculateTF(skimbleshanks_hash, skimbleshanks_totalWords, str);
            float idf = calc9.calculateIDF(massive_hash, skimbleshanks_hash, str);
            float tfidf = calc9.calculateTF_IDF(tf, idf);
            skimbleshanks_hash.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file hashtable
        readFile r10 = new readFile();
        String adWords = r10.readFileMethod(ad);

        String ad_array[] = adWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> ad_hash = new LinearProbingHashST<String, Term>();
        for(String str : ad_array) {
            Term temp = (Term) ad_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                ad_hash.put(str, new Term(str, "ad", i + 1, 0));
            }
            else {
                ad_hash.put(str, new Term(str, "ad", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc10 = new Calculations();
        int ad_totalWords = ad_array.length;

        for (String str : ad_hash.keys()) {
            float tf = calc10.calculateTF(ad_hash, ad_totalWords, str);
            float idf = calc10.calculateIDF(massive_hash, ad_hash, str);
            float tfidf = calc10.calculateTF_IDF(tf, idf);
            ad_hash.get(str).setTd_Idf(tfidf);
        }





        // repeat process for another file hashtable
        readFile r11 = new readFile();
        String namingWords = r11.readFileMethod(naming);

        String naming_array[] = namingWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> naming_hash = new LinearProbingHashST<String, Term>();
        for(String str : naming_array) {
            Term temp = (Term) naming_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                naming_hash.put(str, new Term(str, "naming", i + 1, 0));
            }
            else {
                naming_hash.put(str, new Term(str, "naming", 1, 0));
            }
        }

        // repeat calculations
        Calculations calc11 = new Calculations();
        int naming_totalWords = naming_array.length;

        for (String str : naming_hash.keys()) {
            float tf = calc11.calculateTF(naming_hash, naming_totalWords, str);
            float idf = calc11.calculateIDF(massive_hash, naming_hash, str);
            float tfidf = calc11.calculateTF_IDF(tf, idf);
            naming_hash.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file hashtable
        readFile r12 = new readFile();
        String gumbieWords = r12.readFileMethod(gumbie);

        String gumbie_array[] = gumbieWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");



        LinearProbingHashST<String, Term> gumbie_hash = new LinearProbingHashST<String, Term>();
        for(String str : gumbie_array) {
            Term temp = (Term) gumbie_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                gumbie_hash.put(str, new Term(str, "gumbie", i + 1, 0));
            }
            else {
                gumbie_hash.put(str, new Term(str, "gumbie", 1, 0));
            }
        }


        // repeat calculations
        Calculations calc12 = new Calculations();
        int gumbie_totalWords = gumbie_array.length;

        for (String str : gumbie_hash.keys()) {
            float tf = calc12.calculateTF(gumbie_hash, gumbie_totalWords, str);
            float idf = calc12.calculateIDF(massive_hash, gumbie_hash, str);
            float tfidf = calc12.calculateTF_IDF(tf, idf);
            gumbie_hash.get(str).setTd_Idf(tfidf);
        }





        // repeat process for another file hashtable
        readFile r13 = new readFile();
        String rumWords = r13.readFileMethod(rum);

        String rum_array[] = rumWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");

        LinearProbingHashST<String, Term> rum_hash = new LinearProbingHashST<String, Term>();
        for(String str : rum_array) {
            Term temp = (Term) rum_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                rum_hash.put(str, new Term(str, "rum", i + 1, 0));
            }
            else {
                rum_hash.put(str, new Term(str, "rum", 1, 0));
            }
        }



        // repeat calculations
        Calculations calc13 = new Calculations();
        int rum_totalWords = rum_array.length;

        for (String str : rum_hash.keys()) {
            float tf = calc13.calculateTF(rum_hash, rum_totalWords, str);
            float idf = calc13.calculateIDF(massive_hash, rum_hash, str);
            float tfidf = calc13.calculateTF_IDF(tf, idf);
            rum_hash.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file hashtable
        readFile r14 = new readFile();
        String jelliclesWords = r14.readFileMethod(jellicles);

        String jellicles_array[] = jelliclesWords.replaceAll("[^a-zA-Z]", " ").toLowerCase().split("\\s+");


        LinearProbingHashST<String, Term> jellicles_hash = new LinearProbingHashST<String, Term>();
        for(String str : jellicles_array) {
            Term temp = (Term) jellicles_hash.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                jellicles_hash.put(str, new Term(str, "jellicles", i + 1, 0));
            }
            else {
                jellicles_hash.put(str, new Term(str, "jellicles", 1, 0));
            }
        }


        // repeat process for another file
        Calculations calc14 = new Calculations();
        int jellicles_totalWords = jellicles_array.length;

        for (String str : jellicles_hash.keys()) {
            float tf = calc14.calculateTF(jellicles_hash, jellicles_totalWords, str);
            float idf = calc14.calculateIDF(massive_hash, jellicles_hash, str);
            float tfidf = calc14.calculateTF_IDF(tf, idf);
            jellicles_hash.get(str).setTd_Idf(tfidf);
        }

        //double constructionHashTime = constructionHashStopwatch.elapsedTime();
        long endHashConstructionTime = System.currentTimeMillis();          // end stopwatch for hash construction


        // PREP FOR TASK 3
        // PREP FOR TASK 3
        // PREP FOR TASK 3

        // building BSTs

        BST<String, Term> massive_bst = new BST<String, Term>();    // BST is created
        for(String str : massive_array) {                           // for each str in given array
            Term temp = (Term) massive_bst.get(str);                // create a temp object of Term class
            if (temp != null) {                                     // if object is not null
                Integer i = temp.getFrequency();                    // getFrequency and increment by 1, put all other values into the given BST
                massive_bst.put(str, new Term(str, "massive", i + 1, 0));
            }
            else {                                                  // if object is null, set Freq to 1 and put all other values into the given BST
                massive_bst.put(str, new Term(str, "massive", 1, 0));
            }
        }

        long startBSTConstructionTime = System.currentTimeMillis();     // start BST construction timer
        //Stopwatch constructionBSTstopwatch  = new Stopwatch();

        // repeat process for another file
        BST<String, Term> bustopher_bst = new BST<String, Term>();
        for(String str : bustopher_array) {
            Term temp = (Term) bustopher_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                bustopher_bst.put(str, new Term(str, "bustopher", i + 1, 0));
            }
            else {
                bustopher_bst.put(str, new Term(str, "bustopher", 1, 0));
            }
        }

        // repeat calculations
        Calculations bstCalc1 = new Calculations();

        for (String str : bustopher_bst.keys()) {
            float tf = bstCalc1.calculateTF(bustopher_bst, bustopher_totalWords, str); // made another constructor to take in BSTs
            float idf = bstCalc1.calculateIDF(massive_bst, bustopher_bst, str); // made another constructor to take in BSTs
            float tfidf = bstCalc1.calculateTF_IDF(tf, idf);
            bustopher_bst.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file
        BST<String, Term> growltigers_bst = new BST<String, Term>();
        for(String str : growltigers_array) {
            Term temp = (Term) growltigers_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                growltigers_bst.put(str, new Term(str, "growltigers", i + 1, 0));
            }
            else {
                growltigers_bst.put(str, new Term(str, "growltigers", 1, 0));
            }
        }

        // repeat calculations
        Calculations bstCalc2 = new Calculations();

        for (String str : growltigers_bst.keys()) {
            float tf = bstCalc2.calculateTF(growltigers_bst, growltigers_totalWords, str);
            float idf = bstCalc2.calculateIDF(massive_bst, growltigers_bst, str);
            float tfidf = bstCalc2.calculateTF_IDF(tf, idf);
            growltigers_bst.get(str).setTd_Idf(tfidf);
        }


        // repeat process for another file
        BST<String, Term> gus_bst = new BST<String, Term>();
        for(String str : gus_array) {
            Term temp = (Term) gus_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                gus_bst.put(str, new Term(str, "gus", i + 1, 0));
            }
            else {
                gus_bst.put(str, new Term(str, "gus", 1, 0));
            }
        }

        // repeat calculations
        Calculations bstCalc3 = new Calculations();

        for (String str : gus_bst.keys()) {
            float tf = bstCalc3.calculateTF(gus_bst, gus_totalWords, str);
            float idf = bstCalc3.calculateIDF(massive_bst, gus_bst, str);
            float tfidf = bstCalc3.calculateTF_IDF(tf, idf);
            gus_bst.get(str).setTd_Idf(tfidf);
        }


        // repeat process for another file
        BST<String, Term> macavity_bst = new BST<String, Term>();
        for(String str : macavity_array) {
            Term temp = (Term) macavity_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                macavity_bst.put(str, new Term(str, "macavity", i + 1, 0));
            }
            else {
                macavity_bst.put(str, new Term(str, "macavity", 1, 0));
            }
        }

        // repeat calculations
        Calculations bstCalc4 = new Calculations();

        for (String str : macavity_bst.keys()) {
            float tf = bstCalc4.calculateTF(macavity_bst, macavity_totalWords, str);
            float idf = bstCalc4.calculateIDF(massive_bst, macavity_bst, str);
            float tfidf = bstCalc4.calculateTF_IDF(tf, idf);
            macavity_bst.get(str).setTd_Idf(tfidf);
        }


        // repeat process for another file
        BST<String, Term> mistoffelees_bst = new BST<String, Term>();
        for(String str : mistoffelees_array) {
            Term temp = (Term) mistoffelees_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                mistoffelees_bst.put(str, new Term(str, "mistoffelees", i + 1, 0));
            }
            else {
                mistoffelees_bst.put(str, new Term(str, "mistoffelees", 1, 0));
            }
        }


        // repeat calculations
        Calculations bstCalc5 = new Calculations();

        for (String str : mistoffelees_bst.keys()) {
            float tf = bstCalc5.calculateTF(mistoffelees_bst, mistoffelees_totalWords, str);
            float idf = bstCalc5.calculateIDF(massive_bst, mistoffelees_bst, str);
            float tfidf = bstCalc5.calculateTF_IDF(tf, idf);
            mistoffelees_bst.get(str).setTd_Idf(tfidf);
        }


        // repeat process for another file
        BST<String, Term> mungojerrie_bst = new BST<String, Term>();
        for(String str : mungojerrie_array) {
            Term temp = (Term) mungojerrie_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                mungojerrie_bst.put(str, new Term(str, "mungojerrie", i + 1, 0));
            }
            else {
                mungojerrie_bst.put(str, new Term(str, "mungojerrie", 1, 0));
            }
        }

        // repeat calculations
        Calculations bstCalc6 = new Calculations();

        for (String str : mungojerrie_bst.keys()) {
            float tf = bstCalc6.calculateTF(mungojerrie_bst, mungojerrie_totalWords, str);
            float idf = bstCalc6.calculateIDF(massive_bst, mungojerrie_bst, str);
            float tfidf = bstCalc6.calculateTF_IDF(tf, idf);
            mungojerrie_bst.get(str).setTd_Idf(tfidf);
        }


        // repeat process for another file
        BST<String, Term> awefull_bst = new BST<String, Term>();
        for(String str : awefull_array) {
            Term temp = (Term) awefull_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                awefull_bst.put(str, new Term(str, "awefull", i + 1, 0));
            }
            else {
                awefull_bst.put(str, new Term(str, "awefull", 1, 0));
            }
        }

        // repeat calculations
        Calculations bstCalc7 = new Calculations();

        for (String str : awefull_bst.keys()) {
            float tf = bstCalc7.calculateTF(awefull_bst, awefull_totalWords, str);
            float idf = bstCalc7.calculateIDF(massive_bst, awefull_bst, str);
            float tfidf = bstCalc7.calculateTF_IDF(tf, idf);
            awefull_bst.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file
        BST<String, Term> deuteronomy_bst = new BST<String, Term>();
        for(String str : deuteronomy_array) {
            Term temp = (Term) deuteronomy_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                deuteronomy_bst.put(str, new Term(str, "deuteronomy", i + 1, 0));
            }
            else {
                deuteronomy_bst.put(str, new Term(str, "deuteronomy", 1, 0));
            }
        }

        // repeat calculations
        Calculations bstCalc8 = new Calculations();

        for (String str : deuteronomy_bst.keys()) {
            float tf = bstCalc8.calculateTF(deuteronomy_bst, deuteronomy_totalWords, str);
            float idf = bstCalc8.calculateIDF(massive_bst, deuteronomy_bst, str);
            float tfidf = bstCalc8.calculateTF_IDF(tf, idf);
            deuteronomy_bst.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file
        BST<String, Term> skimbleshanks_bst = new BST<String, Term>();
        for(String str : skimbleshanks_array) {
            Term temp = (Term) skimbleshanks_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                skimbleshanks_bst.put(str, new Term(str, "skimbleshanks", i + 1, 0));
            }
            else {
                skimbleshanks_bst.put(str, new Term(str, "skimbleshanks", 1, 0));
            }
        }


        // repeat calculations
        Calculations bstCalc9 = new Calculations();

        for (String str : skimbleshanks_bst.keys()) {
            float tf = bstCalc9.calculateTF(skimbleshanks_bst, skimbleshanks_totalWords, str);
            float idf = bstCalc9.calculateIDF(massive_bst, skimbleshanks_bst, str);
            float tfidf = bstCalc9.calculateTF_IDF(tf, idf);
            skimbleshanks_bst.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file
        BST<String, Term> ad_bst = new BST<String, Term>();
        for(String str : ad_array) {
            Term temp = (Term) ad_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                ad_bst.put(str, new Term(str, "ad", i + 1, 0));
            }
            else {
                ad_bst.put(str, new Term(str, "ad", 1, 0));
            }
        }


        // repeat calculations
        Calculations bstCalc10 = new Calculations();

        for (String str : ad_bst.keys()) {
            float tf = bstCalc10.calculateTF(ad_bst, ad_totalWords, str);
            float idf = bstCalc10.calculateIDF(massive_bst, ad_bst, str);
            float tfidf = bstCalc10.calculateTF_IDF(tf, idf);
            ad_bst.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file
        BST<String, Term> naming_bst = new BST<String, Term>();
        for(String str : naming_array) {
            Term temp = (Term) naming_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                naming_bst.put(str, new Term(str, "naming", i + 1, 0));
            }
            else {
                naming_bst.put(str, new Term(str, "naming", 1, 0));
            }
        }


        // repeat calculations
        Calculations bstCalc11 = new Calculations();

        for (String str : naming_bst.keys()) {
            float tf = bstCalc11.calculateTF(naming_bst, naming_totalWords, str);
            float idf = bstCalc11.calculateIDF(massive_bst, naming_bst, str);
            float tfidf = bstCalc11.calculateTF_IDF(tf, idf);
            naming_bst.get(str).setTd_Idf(tfidf);
        }


        // repeat process for another file
        BST<String, Term> gumbie_bst = new BST<String, Term>();
        for(String str : gumbie_array) {
            Term temp = (Term) gumbie_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                gumbie_bst.put(str, new Term(str, "gumbie", i + 1, 0));
            }
            else {
                gumbie_bst.put(str, new Term(str, "gumbie", 1, 0));
            }
        }


        // repeat calculations
        Calculations bstCalc12 = new Calculations();

        for (String str : gumbie_bst.keys()) {
            float tf = bstCalc12.calculateTF(gumbie_bst, gumbie_totalWords, str);
            float idf = bstCalc12.calculateIDF(massive_bst, gumbie_bst, str);
            float tfidf = bstCalc12.calculateTF_IDF(tf, idf);
            gumbie_bst.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file
        BST<String, Term> rum_bst = new BST<String, Term>();
        for(String str : rum_array) {
            Term temp = (Term) rum_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                rum_bst.put(str, new Term(str, "rum", i + 1, 0));
            }
            else {
                rum_bst.put(str, new Term(str, "rum", 1, 0));
            }
        }


        // repeat calculations
        Calculations bstCalc13 = new Calculations();

        for (String str : rum_bst.keys()) {
            float tf = bstCalc13.calculateTF(rum_bst, rum_totalWords, str);
            float idf = bstCalc13.calculateIDF(massive_bst, rum_bst, str);
            float tfidf = bstCalc13.calculateTF_IDF(tf, idf);
            rum_bst.get(str).setTd_Idf(tfidf);
        }



        // repeat process for another file
        BST<String, Term> jellicles_bst = new BST<String, Term>();
        for(String str : jellicles_array) {
            Term temp = (Term) jellicles_bst.get(str);
            if (temp != null) {
                Integer i = temp.getFrequency();
                jellicles_bst.put(str, new Term(str, "jellicles", i + 1, 0));
            }
            else {
                jellicles_bst.put(str, new Term(str, "jellicles", 1, 0));
            }
        }


        // repeat calculations
        Calculations bstCalc14 = new Calculations();

        for (String str : jellicles_bst.keys()) {
            float tf = bstCalc14.calculateTF(jellicles_bst, jellicles_totalWords, str);
            float idf = bstCalc14.calculateIDF(massive_bst, jellicles_bst, str);
            float tfidf = bstCalc14.calculateTF_IDF(tf, idf);
            jellicles_bst.get(str).setTd_Idf(tfidf);
        }

        //double BST_constructionTime = constructionBSTstopwatch.elapsedTime();
        long endBSTConstructionTime = System.currentTimeMillis();  // end BST construction timer

        // TASK 3
        // TASK 3
        // TASK 3

        // creating a String array of the amount of Strings in all documents
        String[] massiveKeys = new String[1578];

        int count = 0;
        for(String s : massive_hash.keys()){        // for each string in massive_hash
            massiveKeys[count] = s;                 // put s in massiveKey[count]
            count++;                                // increment count to place next s, in the next place in String[]
        }

        //print unshuffled array (all keys from all documents)
        /*for(String s : massiveKeys){
            System.out.println(s);
        }*/

        class Shuffle {
            // how I shuffle my array to get a random array each time
            void shuffleArray(String[] ar) {
                // If running on Java 6 or older, use `new Random()` on RHS here
                Random rnd = ThreadLocalRandom.current();
                for (int i = ar.length - 1; i > 0; i--) {
                    int index = rnd.nextInt(i + 1);
                    // Simple swap
                    String a = ar[index];
                    ar[index] = ar[i];
                    ar[i] = a;
                }
            }
        }

        Shuffle shuffle = new Shuffle();
        // shuffle array
        shuffle.shuffleArray(massiveKeys);            // shuffle massiveKey[]

        //print shuffled array (all keys from all documents)
        /*for(String s : massiveKeys){
            System.out.println(s);
        }*/

        String[] smallerShuffledKeys = new String[150];         // create a String array of 1/10 size of massiveKeys[]

        int counter = 0;
        for (String s : massiveKeys) {              // for each string in massiveKeys[]
            if(counter < 150) {                     // as long as counter is below 150
                smallerShuffledKeys[counter] = s;   // add to smallerShuffledKeys
                counter++;                          // increment counter
            }
            else {
                break;                              // if counter has reached over 150, break out of loop
            }
        }

        LinearProbingHashST<String, Term> smallerShuffledHash = new LinearProbingHashST<String, Term>();  // create new hashtable
        for(String s : smallerShuffledKeys) {       // put each str from smallerShuffledKeys into new hash table
            smallerShuffledHash.put(s, new Term(s, "hash", 1, (float)0.57)); // random freq and random tf_idf as we don't need that for testing
        }


        BST<String, Term> smallerShuffledBST = new BST<String, Term>();  // create new BST
        for(String s : smallerShuffledKeys) {      // put each str from smallerShuffledKeys into new BST
            smallerShuffledBST.put(s, new Term(s, "BST", 3, (float)0.34)); // random freq and random tf_idf as we don't need that for testing
        }

        // TASK 2 FUNCTIONS
        // TASK 2 FUNCTIONS
        // TASK 2 FUNCTIONS

        // Search class containing Search(String key) function and top10(String document) function
        class Search {

            ArrayList<String> ans = new ArrayList<String>(); // create an arrayList to hold data

            ArrayList<String> search(String key) {
                if(bustopher_hash.get(key) != null) {                       // if value of key is not null
                    String doc1 = bustopher_hash.get(key).getDocument();    // store document name of key
                    int freq1 = bustopher_hash.get(key).getFrequency();     // store frequency of key
                    float tf_idf1 = bustopher_hash.get(key).getTf_Idf();    // store tf_idf score of key
                    ans.add(doc1);                                          // add document name to ArrayList
                    ans.add(Integer.toString(freq1));                       // add frequency to ArrayList
                    ans.add(String.valueOf(tf_idf1));                       // add tf_idf to ArrayList
                }                                                           // repeat for all docs
                if(growltigers_hash.get(key) != null) {
                    String doc2 = growltigers_hash.get(key).getDocument();
                    int freq2 = growltigers_hash.get(key).getFrequency();
                    float tf_idf2 = growltigers_hash.get(key).getTf_Idf();
                    ans.add(doc2);
                    ans.add(Integer.toString(freq2));
                    ans.add(String.valueOf(tf_idf2));
                }
                if(gus_hash.get(key) != null) {
                    String doc3 = gus_hash.get(key).getDocument();
                    int freq3 = gus_hash.get(key).getFrequency();
                    float tf_idf3 = gus_hash.get(key).getTf_Idf();
                    ans.add(doc3);
                    ans.add(Integer.toString(freq3));
                    ans.add(String.valueOf(tf_idf3));
                }
                if(macavity_hash.get(key) != null) {
                    String doc4 = macavity_hash.get(key).getDocument();
                    int freq4 = macavity_hash.get(key).getFrequency();
                    float tf_idf4 = macavity_hash.get(key).getTf_Idf();
                    ans.add(doc4);
                    ans.add(Integer.toString(freq4));
                    ans.add(String.valueOf(tf_idf4));
                }
                if(mistoffelees_hash.get(key) != null) {
                    String doc5 = mistoffelees_hash.get(key).getDocument();
                    int freq5 = mistoffelees_hash.get(key).getFrequency();
                    float tf_idf5 = mistoffelees_hash.get(key).getTf_Idf();
                    ans.add(doc5);
                    ans.add(Integer.toString(freq5));
                    ans.add(String.valueOf(tf_idf5));
                }
                if(mungojerrie_hash.get(key) != null) {
                    String doc6 = mungojerrie_hash.get(key).getDocument();
                    int freq6 = mungojerrie_hash.get(key).getFrequency();
                    float tf_idf6 = mungojerrie_hash.get(key).getTf_Idf();
                    ans.add(doc6);
                    ans.add(Integer.toString(freq6));
                    ans.add(String.valueOf(tf_idf6));
                }
                if(awefull_hash.get(key) != null) {
                    String doc7 = awefull_hash.get(key).getDocument();
                    int freq7 = awefull_hash.get(key).getFrequency();
                    float tf_idf7 = awefull_hash.get(key).getTf_Idf();
                    ans.add(doc7);
                    ans.add(Integer.toString(freq7));
                    ans.add(String.valueOf(tf_idf7));
                }
                if(deuteronomy_hash.get(key) != null) {
                    String doc8 = deuteronomy_hash.get(key).getDocument();
                    int freq8 = deuteronomy_hash.get(key).getFrequency();
                    float tf_idf8 = deuteronomy_hash.get(key).getTf_Idf();
                    ans.add(doc8);
                    ans.add(Integer.toString(freq8));
                    ans.add(String.valueOf(tf_idf8));
                }
                if(skimbleshanks_hash.get(key) != null) {
                    String doc9 = skimbleshanks_hash.get(key).getDocument();
                    int freq9 = skimbleshanks_hash.get(key).getFrequency();
                    float tf_idf9 = skimbleshanks_hash.get(key).getTf_Idf();
                    ans.add(doc9);
                    ans.add(Integer.toString(freq9));
                    ans.add(String.valueOf(tf_idf9));
                }
                if(ad_hash.get(key) != null) {
                    String doc10 = ad_hash.get(key).getDocument();
                    int freq10 = ad_hash.get(key).getFrequency();
                    float tf_idf10  = ad_hash.get(key).getTf_Idf();
                    ans.add(doc10);
                    ans.add(Integer.toString(freq10));
                    ans.add(String.valueOf(tf_idf10));
                }
                if(naming_hash.get(key) != null) {
                    String doc11 = naming_hash.get(key).getDocument();
                    int freq11 = naming_hash.get(key).getFrequency();
                    float tf_idf11  = naming_hash.get(key).getTf_Idf();
                    ans.add(doc11);
                    ans.add(Integer.toString(freq11));
                    ans.add(String.valueOf(tf_idf11));
                }
                if(gumbie_hash.get(key) != null) {
                    String doc12 = gumbie_hash.get(key).getDocument();
                    int freq12 = gumbie_hash.get(key).getFrequency();
                    float tf_idf12  = gumbie_hash.get(key).getTf_Idf();
                    ans.add(doc12);
                    ans.add(Integer.toString(freq12));
                    ans.add(String.valueOf(tf_idf12));
                }
                if(rum_hash.get(key) != null) {
                    String doc13 = rum_hash.get(key).getDocument();
                    int freq13 = rum_hash.get(key).getFrequency();
                    float tf_idf13  = rum_hash.get(key).getTf_Idf();
                    ans.add(doc13);
                    ans.add(Integer.toString(freq13));
                    ans.add(String.valueOf(tf_idf13));
                }
                if(jellicles_hash.get(key) != null) {
                    String doc14 = jellicles_hash.get(key).getDocument();
                    int freq14 = jellicles_hash.get(key).getFrequency();
                    float tf_idf14  = jellicles_hash.get(key).getTf_Idf();
                    ans.add(doc14);
                    ans.add(Integer.toString(freq14));
                    ans.add(String.valueOf(tf_idf14));
                }
                if(smallerShuffledHash.get(key) != null) {
                    String doc15 = smallerShuffledHash.get(key).getDocument();
                    int freq15 = smallerShuffledHash.get(key).getFrequency();
                    float tf_idf15  = smallerShuffledHash.get(key).getTf_Idf();
                    ans.add(doc15);
                    ans.add(Integer.toString(freq15));
                    ans.add(String.valueOf(tf_idf15));
                }
                if(smallerShuffledBST.get(key) != null) {
                    String doc16 = smallerShuffledBST.get(key).getDocument();
                    int freq16 = smallerShuffledBST.get(key).getFrequency();
                    float tf_idf16  = smallerShuffledBST.get(key).getTf_Idf();
                    ans.add(doc16);
                    ans.add(Integer.toString(freq16));
                    ans.add(String.valueOf(tf_idf16));
                }
                return ans;                                         // return arrayList with the correct information
                                                                    // Answers will be in the form of: [term, freq, tf_idf, term, freq, tf_idf, etc]
            }



            LinearProbingHashST<String, Float> correctHash = new LinearProbingHashST<String, Float>();   // hashtable to store correct terms and tf_idf scores of given document
            LinearProbingHashST<String, Float> sortedTop10 = new LinearProbingHashST<String, Float>();   // hashtable to store ordered terms and tf_idf scores
            ArrayList<Float> scores_arrayList = new ArrayList<Float>();                                  // arrayList to hold all correct tf_idf scores

            void top10(String document) {
                for(String str : bustopher_hash.keys()) {                       // for each string in hashtable
                    if (bustopher_hash.get(str).getDocument() == document) {    // check if the key's document field matches the given document
                        float td_idf = bustopher_hash.get(str).getTf_Idf();     // if it does put tf_idf score of that string in a variable
                        correctHash.put(str, td_idf);                           // put the term and correlating tf_idf score in correctHash
                    }
                }                                                               // repeat
                for(String str : growltigers_hash.keys()) {
                    if (growltigers_hash.get(str).getDocument() == document) {
                        float td_idf = growltigers_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : gus_hash.keys()) {
                    if (gus_hash.get(str).getDocument() == document) {
                        float td_idf = gus_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : macavity_hash.keys()) {
                    if (macavity_hash.get(str).getDocument() == document) {
                        float td_idf = macavity_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : mistoffelees_hash.keys()) {
                    if (mistoffelees_hash.get(str).getDocument() == document) {
                        float td_idf = mistoffelees_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : mungojerrie_hash.keys()) {
                    if (mungojerrie_hash.get(str).getDocument() == document) {
                        float td_idf = mungojerrie_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : awefull_hash.keys()) {
                    if (awefull_hash.get(str).getDocument() == document) {
                        float td_idf = awefull_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : deuteronomy_hash.keys()) {
                    if (deuteronomy_hash.get(str).getDocument() == document) {
                        float td_idf = deuteronomy_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : skimbleshanks_hash.keys()) {
                    if (skimbleshanks_hash.get(str).getDocument() == document) {
                        float td_idf = skimbleshanks_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : ad_hash.keys()) {
                    if (ad_hash.get(str).getDocument() == document) {
                        float td_idf = ad_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : naming_hash.keys()) {
                    if (naming_hash.get(str).getDocument() == document) {
                        float td_idf = naming_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : gumbie_hash.keys()) {
                    if (gumbie_hash.get(str).getDocument() == document) {
                        float td_idf = gumbie_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : rum_hash.keys()) {
                    if (rum_hash.get(str).getDocument() == document) {
                        float td_idf = rum_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }
                for(String str : jellicles_hash.keys()) {
                    if (jellicles_hash.get(str).getDocument() == document) {
                        float td_idf = jellicles_hash.get(str).getTf_Idf();
                        correctHash.put(str, td_idf);
                    }
                }

                // correct hash has correct terms and correct values
              /* for (String s : correctHash.keys()) {
                    System.out.printf("Term: %s\n" + "Score: %f\n", s, correctHash.get(s));
                }
              */

                for(String s : correctHash.keys()) {                // for each string in correctHash
                    scores_arrayList.add(correctHash.get(s));       // add each tf_idf score into scores_arrayList
                }

                Collections.sort(scores_arrayList, Collections.reverseOrder()); // sort tf_idf scores in reverse order (descending)

                // sorting correctly from greatest to least
                /*System.out.println("Sorted?");
                for(Float f : scores_arrayList) {
                    System.out.println(f);
                }*/

                int hash_length = 0;
                    for (Float f : scores_arrayList) {              // for each float in scores_arrayList
                        for (String str : correctHash.keys()) {     // for each str in correctHash
                            if (correctHash.get(str) == f) {        // if the str's value is equal to the float
                                sortedTop10.put(str, f);            // put the str and the float into sortedTop10 hashtable
                                System.out.printf("Term: %s\n", str);       // print out the term
                                System.out.printf("Tf_Idf: %f\n",f);        // print out the tf_idf score
                            }
                        }
                        hash_length = hash_length + 1;              // increment hash_length by 1 everytime we look at a new float value from scores_arrayList
                        if(hash_length == 10) {                     // if hash_length gets to 10
                            break;                                  // break out of loop (makes sure only top 10 of the values from scores_arrayList are in sortedTop10
                     }
                }

            }

        }



        System.out.println("Search Function run with the string 'growltiger': \n");
        Search h = new Search();
        System.out.println(h.search("growltiger")+ "\n\n");
        System.out.println("Top10 Function run with the document 'bustopher': \n");
        h.top10("bustopher");
        System.out.println("\n");

        //please comment out 1 of the functions below to test separately!! thanks :))

        long bstSearch_startTime = System.nanoTime();      // start search timer for BST
        h.search("cat");
        long bstSearch_endTime = System.nanoTime();        // end search timer for BST

        /*long hashSearch_startTime = System.nanoTime();     // start search timer for hash
        h.search("cat");
        long hashSearch_endTime = System.nanoTime();*/     // end search timer for hash

        System.out.printf("Hastable Construction Time in milliseconds: " + (endHashConstructionTime - startHashConstructionTime) + "\n");
        System.out.printf("BST Construction Time in milliseconds: " + (endBSTConstructionTime - startBSTConstructionTime) + "\n");

        // make sure to comment out one of the following statements below based on which function you commented out above!!

        //System.out.printf("Hastable Search Time in nanoseconds: " + (hashSearch_endTime - hashSearch_startTime) + "\n");
        System.out.printf("BST Search Time in nanoseconds: " + (bstSearch_endTime - bstSearch_startTime)+ "\n");

    }


 }

