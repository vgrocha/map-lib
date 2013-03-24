# map_lib

A Clojure library designed to manipulate maps

## Usage

Sometimes I wanted to manipulate maps and iterate through values, using the keys or not... so here it is

    (map-map inc {:a 1 :b 3})
    ;;=> {:a 2 :b 4}

    (with-map-> {:a [1 2 3] :b [4 5 6]}
      (nth 1)
      inc)
    ;;=> {:a 3 :b 6}
   
    (with-map->> {:a [1 2 3] :b [4 5 6]}
      (reduce +)
      inc)
    ;;=> {:a 7 :b 16}

   
## License

Copyright © 2013

Distributed under the Eclipse Public License, the same as Clojure.
