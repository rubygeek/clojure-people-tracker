(ns people-tracker.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [people-tracker.core :as core]))
 
(deftest find-index-test
  (testing
    (let [people (atom [{:name "bob"}]) ]  
      (is (= 0 (core/find-index people "bob"))))))

(deftest add-person-test
  (let [people (atom [])]
    (is (= [{:name "bob"}] (core/add-person people {:name "bob"})))))

(deftest remove-person-test 
  (let [people (atom [{:name "bob"} {:name "sal"} {:name "jay"}])
        after-remove-sal (core/remove-person people "sal")
        duplicate-call   (core/remove-person people "sal")]
    (is (= [{:name "bob"} {:name "jay"}] after-remove-sal))
    (is (= [{:name "bob"} {:name "jay"}] duplicate-call))))

(deftest update-person-test 
  (let [people (atom [{:name "ann"}])
        after-add-ann (core/update-person people "ann" {:phone "123.133.1231"})]
    (is (= [{:name "ann" :phone "123.133.1231"}] after-add-ann))))


