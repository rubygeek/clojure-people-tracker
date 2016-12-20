(ns people-tracker.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [people-tracker.core :as core]))
 
(deftest add-person-test
  (let [people (atom [])]
    (is (= [{:name "bob"}] (core/add-person people {:name "bob"})))))

(deftest remove-person-test 
  (let [people (atom [{:name "bob"} {:name "sal"} {:name "jay"}])
        after-sal (core/remove-person people "sal")
        duplicate-call (core/remove-person people "sal")]
    (is (= [{:name "bob"} {:name "jay"}] after-sal))
    (is (= [{:name "bob"} {:name "jay"}] duplicate-call))))

