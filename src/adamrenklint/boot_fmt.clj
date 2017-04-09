(ns adamrenklint.boot-fmt
  {:boot/export-tasks true}
  (:require [boot.core :as core]))

(core/deftask fmt
  "Format files in :source-paths using cljfmt"
  []
  (require 'cljfmt.core 'clojure.java.io)
  (doseq [path (core/get-env :source-paths)]
    (let [reformat-string (resolve 'cljfmt.core/reformat-string)
          file (resolve 'clojure.java.io/file)
          fmt-file (fn [f]
                     (println "Formatting" (.getPath f))
                     (spit f (reformat-string (slurp f))))
          clj-file? (fn [f]
                      (and (.exists f) (.isFile f) (not (.isHidden f))
                           (contains? #{"clj" "cljs" "cljc" "cljx" "boot"}
                                      (last (.split (.toLowerCase (.getName f)) "\\.")))))
          f (file path)]
      (when (.exists f)
        (doall (map fmt-file (filter clj-file? (if (.isDirectory f) (file-seq f) [f]))))))))
