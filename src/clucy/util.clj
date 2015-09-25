(ns clucy.util
  (:refer-clojure :exclude [pop])
  (:import
   (java.io File
            ByteArrayInputStream)
   (java.nio.charset StandardCharsets)
   (java.util ArrayList
              Stack)))

(defn ^File make-temp-dir
  ([^String prefix]
   (let [path (new File "/var/tmp/")]
     (let [temp-file (File/createTempFile prefix "tmp" path)]
       (.delete temp-file)
       (.mkdir temp-file)
       temp-file)))
  ([] (make-temp-dir "lucene")))

(defn stack ^Stack [items]
  (let [s (Stack.)]
    (dorun (map #(.push s %) items))
    s))

(defn pop [^Stack s]
  (if (not (.empty s))
    (.pop s)))

(defn llast [x]
  (-> x last last))

(defn lazy-flatten [s]
  (when-not (empty? s)
    (concat (first s) (lazy-seq (lazy-flatten (rest s))))))

(defn string->stream [s]
  (ByteArrayInputStream.
   (.getBytes s StandardCharsets/UTF_8)))
