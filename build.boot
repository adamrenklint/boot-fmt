(def project 'adamrenklint/boot-autofmt)
(def version "0.1.0")

(set-env!
 :source-paths #{"src"}
 :dependencies '[[cljfmt "0.3.0"]
                 [adzerk/bootlaces "0.1.13" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[adamrenklint.boot-autofmt :refer [autofmt]])

(bootlaces! version)

(deftask release []
  (comp (build-jar)
        (push-release)
        (dosh "git" "push" "--tags")))

(task-options!
  pom {:project     project
       :version     version
       :description "Boot task to automatically fix Clojure(Script) syntax formatting with cljfmt"
       :url         "https://github.com/adamrenklint/boot-autofmt"
       :scm         {:url "https://github.com/adamrenklint/boot-autofmt"}
       :license     {"MIT" "https://github.com/adamrenklint/boot-autofmt/blob/master/LICENSE"}})
