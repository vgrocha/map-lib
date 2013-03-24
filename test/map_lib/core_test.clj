(ns map-lib.core-test
  (:use clojure.test
        map-lib.core))

(deftest map-map-test
  (testing "map-map function test"
    (is (= {:a 2, :b 4}
           ))))

(deftest with-map->-test
  (testing "with-map-> function test"
    (is (= {:a 4, :b 6}
           (with-map-> {:a 2 :b 4}
             inc
             inc)))
    (is (= {:a 7, :b 16}
           (with-map->> {:a [1 2 3] :b [4 5 6]}
             (reduce +)
             inc)))))

(deftest with-map->>-test
  (testing "with-map->> function test"
    (is (= {:a 4, :b 6}
           (with-map->> {:a 2 :b 4}
             inc
             inc)))
    (is (= {:a 7, :b 16}
           (with-map->> {:a [1 2 3] :b [4 5 6]}
             (reduce +)
             inc)))))

