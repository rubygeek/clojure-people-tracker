(ns people-tracker.core)

;; user map
;;    name: str  
;;    company: str
;;    email: str
;;    history: vec of map
;;    active: false

(def people (atom []))

;; this function has side effects - println
(defn display-list []
  (doall (map-indexed #(println %1 ":" (:name %2)) @people ))
  (println "----")
  :done )

;; internal functions

(defn remove-at-index [coll idx] 
  (flatten (conj (subvec coll 0 idx) (subvec coll (inc idx)))))


;; repl functions 
 
(defn add-person
  ([person-data]
    (add-person people person-data)) 
  ([people person-data]
   (swap! people conj person-data)))

(defn remove-person
  ([name]
    (remove-person people name))
  ([people name]
   (let [idx (.indexOf @people {:name name})]
     (if (not= idx -1) 
       (reset! people (remove-at-index @people idx))
       @people))))

;; todo
(defn update-person [name data])
