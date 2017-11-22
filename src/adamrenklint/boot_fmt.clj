(ns adamrenklint.boot-fmt
  {:boot/export-tasks true}
  (:require [boot.core :as core]
            [cljfmt.core :refer [reformat-string default-indents]]
            [clojure.java.io :refer [file]]))

(core/deftask fmt
  "Format files in :source-paths using cljfmt"
  [i indents INDENTS edn "Map of var symbols to indentation rules, i.e. {symbol [& rules]}"]
  (core/with-pass-thru _
    (doseq [path (core/get-env :source-paths)]
      (let [indents (merge default-indents indents)
            fmt-file (fn [f]
                       (let [old (slurp f)
                             new (reformat-string (slurp f) {:indents indents})
                             same? (= new old)]
                         (when-not same?
                           (println "Formatted" (.getPath f))
                           (spit f new))))

            clj-file? (fn [f]
                        (and (.exists f) (.isFile f) (not (.isHidden f))
                             (contains? #{"clj" "cljs" "cljc" "cljx" "boot"}
                                        (last (.split (.toLowerCase (.getName f)) "\\.")))))
            f (file path)]
        (when (.exists f)
          (doall (map fmt-file (filter clj-file? (if (.isDirectory f) (file-seq f) [f])))))))))
