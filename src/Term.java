public class Term {

    private String word;
    private String document;
    private int frequency;
    private float tf_idf;

    public Term(String word_arg, String document_arg, int frequency_arg, float tf_idf_arg) {
        this.word = word_arg;
        this.document = document_arg;
        this.frequency = frequency_arg;
        this.tf_idf = tf_idf_arg;
    }

    public String getWord() {
        return word;
    }

    public String getDocument() {
        return document;
    }

    public int getFrequency() {
        return frequency;
    }

    public float getTf_Idf() {
        return tf_idf;
    }

    public void setTd_Idf(float calculated) {
        tf_idf = calculated;
    }

}
