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

;; helper functions

(defn remove-at-index [coll idx] 
  (flatten (conj (subvec coll 0 idx) (subvec coll (inc idx)))))

(defn find-index [name]
  (.indexOf @people {:name name}))

;; side effects

(defn display-list-people []
  (doall (map-indexed #(println %1 ":" (:name %2)) @people ))
  (println "----")
  :done )


(defn add-person
  ([person-data]
    (add-person people person-data)) 
  ([people person-data]
   (swap! people conj person-data)))

;; consider returning exception when idx not found 

(defn remove-person
  ([name]
    (remove-person people name))
  ([people name]
   (let [idx (find-index name)]
     (if (not= idx -1) 
       (reset! people (remove-at-index @people idx))
       @people))))

(defn update-person [name data]
  ([name data]
    (update-person people name data))
  ([people name data]
    (let [idx (find-index name)
          person (get @people idx)
          updated (merge person data)]
      (if (not= idx -1)
        (swap! people assoc idx updated)
        :invalid-update))))
