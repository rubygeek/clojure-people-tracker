(ns people-tracker.core
  )

;;    name: str  
;;    company: str
;;    email: str
;;    history: vec of map
;;    active: false

(def people (atom []))

(defn list-people [] 
  @people)

;; side effects
(defn display-list-people []
  (doall (map-indexed #(println %1 ":" (:name %2)) @people ))
  (println "----")
  :done )

(defn remove-at-index [coll idx] 
  (flatten (conj (subvec coll 0 idx) (subvec coll (inc idx)))))


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

(defn update-person [name data])
