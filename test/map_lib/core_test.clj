(ns map-lib.core-test
  (:use clojure.test
        map-lib.core))

(deftest map-vals-test
  (testing "map-map function test"
    (is (= {:a 2, :b 5}
           (map-vals inc {:a 1 :b 4})))
    (is (= {:a ["k:a" 2], :b ["k:b" 5]}
           (map-vals #(vector (str "k" *key* ) (inc %)) {:a 1 :b 4})))))

(deftest with-vals->-test
  (testing "with-vals-> function test"
    (is (= {:a 4, :b 6}
           (with-vals-> {:a 2 :b 4}
             inc
             inc)))
    (is (= {:a 7, :b 16}
           (with-vals->> {:a [1 2 3] :b [4 5 6]}
             (reduce +)
             inc)))))

(deftest with-vals->>-test
  (testing "with-vals->> function test"
    (is (= {:a 4, :b 6}
           (with-vals->> {:a 2 :b 4}
             inc
             inc)))
    (is (= {:a 7, :b 16}
           (with-vals->> {:a [1 2 3] :b [4 5 6]}
             (reduce +)
             inc)))))


(deftest coll-to-map-test
  (testing "coll-to-map function test"
    (is (= {1 2, 4 5, 6 7}
           (coll-to-map inc [1 4 6])))
    (is (= {1 "1->value!", 4 "4->value!", 6 "6->value!"}
           (coll-to-map str [1 4 6] "->value!")))))