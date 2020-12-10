public class Calculations {
    final float test_docs = 14;

    public float calculateTF(LinearProbingHashST<String, Term> test_hash, int total_words, String term) {
        // tf calc for Hash
        float freq = test_hash.get(term).getFrequency();  // get frequency
        float tf = freq/total_words;                      // frequency/total words for tf
        return tf;
    }

    public float calculateTF(BST<String, Term> test_hash, int total_words, String term) {
        // tf calc for BST
        float freq = test_hash.get(term).getFrequency();
        float tf = freq/total_words;
        return tf;
    }

    public float calculateIDF(LinearProbingHashST<String, Term> large_hash, LinearProbingHashST<String, Term> test_hash, String term) {
        //idf calcs for Hash
        float idf = 0;
        float df = large_hash.get(term).getFrequency(); // number of times the term is used across all documents
        double divide = (test_docs)/(df);
        idf = (float) Math.log10(divide+1); // test_docs = 14 (add 1 to make sure log is larger than 1 for positive idf values)
        return idf;
    }

    public float calculateIDF(BST<String, Term> large_hash, BST<String, Term> test_hash, String term) {
        //idf calcs for BST
        float idf = 0;
        float df = large_hash.get(term).getFrequency(); // number of times the term is used across all documents
        double divide = (test_docs)/(df+1);
        idf = (float) Math.log10(divide+1); // test_docs = 14 (add 1 to make sure log is larger than 1 for positive idf values)
        return idf;
    }

    public float calculateTF_IDF(float tf, float idf) {
        return tf*idf;
    }



}
