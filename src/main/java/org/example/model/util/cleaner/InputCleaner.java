package org.example.model.util.cleaner;

/**
 * Searches for malware inputs from
 * any string and then cleanses it.
 */
public interface InputCleaner {

    /**
     * Scans the given input and purifies it from unwanted symbols.
     *
     * @param string the target string
     *
     * @return cleansed string
     */
    String cleanse(String string);
}
