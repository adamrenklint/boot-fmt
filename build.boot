(def project 'adamrenklint/boot-fmt)
(def version "1.0.0")

(set-env!
 :source-paths #{"src"}
 :dependencies '[[cljfmt "0.3.0"]
                 [adzerk/bootlaces "0.1.13" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[adamrenklint.boot-fmt :refer [fmt]])

(bootlaces! version)

(deftask release []
  (comp (build-jar)
        (push-release)
        (dosh "git" "push" "--tags")))

(task-options!
  pom {:project     project
       :version     version
       :description "Boot task to automatically fix Clojure(Script) syntax formatting with cljfmt"
       :url         "https://github.com/adamrenklint/boot-fmt"
       :scm         {:url "https://github.com/adamrenklint/boot-fmt"}
       :license     {"MIT" "https://github.com/adamrenklint/boot-fmt/blob/master/LICENSE"}})
