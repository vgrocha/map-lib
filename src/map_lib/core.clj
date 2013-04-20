(ns map-lib.core)

;;deprecated, kept here for illustrative purposes
#_(defn map-map
  "Maps a 'fn-name' to a collections of maps with respect to its keys"
  ([fn-name & colls]
     (let [ks (set (mapcat keys colls))]
       (into {}
             (for [k (seq ks)]
               [k
                (apply fn-name (map #(get % k) colls))])))))


(defmacro map-vals
  "Maps 'fn-name' to the map values.
   Optionally the map key is inside '*key*' variable is available.

   (map-vals inc {:a 1 :b 3})
   ;;=> {:a 2 :b 4}"
  [fn-name my-map]
  `(into {}
         (for [[~'*key* v#] ~my-map]
           [~'*key* (~fn-name v#)])))

(defmacro with-vals->
  "Threads each map value with ->

   (with-vals-> {:a 2 :b 4}
      inc
      inc)
   ;;=> {:a 4 :b 6}

   (with-vals-> {:a [1 2 3] :b [4 5 6]}
      (nth 1)
      inc)
   ;;=> {:a 3 :b 6}"
  [my-map & body]
  `(into {}
         (for [[~'*key* v#] ~my-map]
           [~'*key*
            (-> v#
                ~@body)])))

(defmacro with-vals->>
  "Threads each map value with ->>

   (with-vals->> {:a 2 :b 4}
      inc
      inc)
   ;;=> {:a 4 :b 6}

   (with-vals->> {:a [1 2 3] :b [4 5 6]}
      (reduce +)
      inc)
   ;;=> {:a 7 :b 16}"
  [my-map & body]
  `(into {}
         (for [[~'*key* v#] ~my-map]
           [~'*key*
            (->> v#
                 ~@body)])))



#_(defmacro map-keys [fn-name my-map]
  `(into {}
         (for [[k# ~'*value*] ~my-map]
           [(~fn-name k#)
            ~'*value*])))

#_(defmacro with-keys-> [my-map & body]
  `(into {}
         (for [[k# ~'*value*] ~my-map]
           [(-> k#
                ~@body)
            ~'*value*])))

#_(defmacro with-keys->> [my-map & body]
  `(into {}
         (for [[k# ~'*value*] ~my-map]
           [(->> k#
                ~@body)
            ~'*value*])))

#_(defn vals-map )

(defn coll-to-map
  "Maps each value 'v' from the 'coll' to (apply fnn-name v args)

   e.g.
     =>(coll-to-map inc [1 4 6])
     ;;=> {1 2, 4 5, 6 7}

     =>(coll-to-map str [1 4 6] \"number\")
     ;;=> {1 \"1number\", 4 \"4number\", 6 \"6number\"}"
  [fn-name coll & args]
  (into {} (for [c coll]
             [c (apply fn-name c args)])))