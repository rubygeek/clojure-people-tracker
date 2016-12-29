(ns people-tracker.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [people-tracker.core :as core]))
 
(deftest find-index-test
  (testing "when there is only one"
    (let [people (atom [{:name "bob"}]) ]  
      (is (= 0 (core/find-index people "bob")))))

  (testing "when there are three"
    (let [people (atom [{:name "bob"} {:name "jay"} {:name "gil"}])]
    (is (= 1 (core/find-index people "jay"))))))

(deftest add-person-test
  (testing "adding one" 
    (let [people (atom [])]
      (is (= [{:name "bob"}] (core/add-person people {:name "bob"})))))

  (testing "adding one to a list of 3"
    (let [people (atom [{:name "a"} {:name "b"} {:name "c"}])
          adding-one (core/add-person people {:name "d"})]
      (is (= [{:name "a"} {:name "b"} {:name "c"} {:name "d"}] adding-one)))))

(deftest remove-person-test 
  (testing "removing person" 
    (let [people (atom [{:name "bob"} {:name "sal"} {:name "jay"}])
          after-remove-sal (core/remove-person people "sal")]
      (is (= [{:name "bob"} {:name "jay"}] after-remove-sal))))

  (testing "removing person again returns same"
    (let [people (atom [{:name "bob"} {:name "sal"} {:name "jay"}])
          after-remove-sal (core/remove-person people "sal")
          duplicate-result (core/remove-person people "sal")]
      (is (= [{:name "bob"} {:name "jay"}] after-remove-sal))
      (is (= [{:name "bob"} {:name "jay"}] duplicate-result)))))

(deftest update-person-test 
  (testing "updating a person map"
    (let [people (atom [{:name "jay"} {:name "ann"} {:name "bob"}])
          update-result (core/update-person people "ann" {:phone "123.133.1231"})
          expected [{:name "jay"} {:name "ann" :phone "123.133.1231"} {:name "bob"}]]
      (is (= expected update-result))))

  (testing "updating when name is not found returns existing list unchanged"
    (let [people (atom [{:name "jay"} {:name "ann"} {:name "bob"}])
          update-result (core/update-person people "mark" {:phone "123.133.1231"})
          expected [{:name "jay"} {:name "ann"} {:name "bob"}]]
      (is (= expected update-result)))))

