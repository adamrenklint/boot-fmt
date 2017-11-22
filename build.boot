(def project 'adamrenklint/boot-fmt)
(def version "1.2.1")

(set-env!
 :source-paths #{"src"}
 :dependencies '[[cljfmt "0.5.7"]
                 [adzerk/bootlaces "0.1.13" :scope "test"]
                 [tolitius/boot-check "0.1.6" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[adamrenklint.boot-fmt :refer [fmt]]
         '[tolitius.boot-check :as check])

(bootlaces! version)

(ns-unmap 'boot.user 'format)

(deftask release []
  (comp (build-jar)
        (push-release)
        (with-pass-thru _ (dosh "git" "push" "--tags"))))

(deftask check []
  (comp (check/with-yagni)
        (check/with-eastwood)
        (check/with-kibit)
        (check/with-bikeshed)))

(deftask format []
  (fmt :indents '{foo [[:block 1]]}))

(task-options!
  pom {:project     project
       :version     version
       :description "Boot task to automatically fix Clojure(Script) syntax formatting with cljfmt"
       :url         "https://github.com/adamrenklint/boot-fmt"
       :scm         {:url "https://github.com/adamrenklint/boot-fmt"}
       :license     {"MIT" "https://github.com/adamrenklint/boot-fmt/blob/master/LICENSE"}})
