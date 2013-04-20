# map_lib

A Clojure library designed to manipulate maps

## Motivation
Sometimes map manipulation can be cumbersome, so I designed this small library for building maps, manipulating maps values (and soon manipulating maps keys)

## Usage
Examples

    (require '[map-lib.core :as ml])

    (ml/map-vals inc {:a 1 :b 3})
    ;;=> {:a 2 :b 4}

    (ml/with-vals-> {:a [1 2 3] :b [4 5 6]}
      (nth 1)
      inc)
    ;;=> {:a 3 :b 6}
   
    (ml/with-vals->> {:a [1 2 3] :b [4 5 6]}
      (reduce +)
      inc)
    ;;=> {:a 7 :b 16}

    (ml/coll-to-map inc [1 4 6])
    ;;=> {1 2, 4 5, 6 7}
    
## Versions
# "0.1.1"

* Renamed a few functions (sorry about breaking api), e.g. 'map-map' to 'map-vals', 'with-map->' to 'with-vals->>' because it makes more sense
* Added 'coll-to-map' 

#  "0.1.0-SNAPSHOT"
First version, just some basic functions
   
## License

Copyright © 2013

Distributed under the Eclipse Public License, the same as Clojure.
