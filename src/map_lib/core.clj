(ns map-lib.core)

(defmacro map-map
  "Maps 'fn-name' to the map values.
   Optionally, *key* value is available.

   (map-map inc {:a 1 :b 3})
   ;;=> {:a 2 :b 4}"
  [fn-name my-map]
  `(into {}
         (for [[~'*key* v#] ~my-map]
           [~'*key* (~fn-name v#)])))

(defmacro with-map->
  "Threads each map value with ->

   (with-map-> {:a 2 :b 4}
      inc
      inc)
   ;;=> {:a 4 :b 6}

   (with-map-> {:a [1 2 3] :b [4 5 6]}
      (nth 1)
      inc)
   ;;=> {:a 3 :b 6}"
  [my-map & body]
  `(into {}
         (for [[~'*key* v#] ~my-map]
           [~'*key*
            (-> v#
                ~@body)])))

(defmacro with-map->>
  "Threads each map value with ->>

   (with-map->> {:a 2 :b 4}
      inc
      inc)
   ;;=> {:a 4 :b 6}

   (with-map->> {:a [1 2 3] :b [4 5 6]}
      (reduce +)
      inc)
   ;;=> {:a 7 :b 16}"
  [my-map & body]
  `(into {}
         (for [[~'*key* v#] ~my-map]
           [~'*key*
            (->> v#
                 ~@body)])))