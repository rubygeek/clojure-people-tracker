(ns people-tracker.core)

;; user map
;;    name: str  
;;    company: str
;;    email: str
;;    history: vec of map
;;    active: false

(def people (atom []))

;; helper functions

(defn remove-at-index [coll idx] 
  (flatten (conj (subvec coll 0 idx) (subvec coll (inc idx)))))

(defn find-index
  ([name]
    (find-index people name))
  ([people name]
   (.indexOf @people {:name name})))

;; this function has side effects - println
(defn display-list []
  (doall (map-indexed #(println %1 ":" (:name %2)) @people ))
  :done)

;; repl functions 
 
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
   (let [idx (find-index people name)]
     (if (not= idx -1) 
       (reset! people (remove-at-index @people idx))
       @people))))

(defn update-person
  ([name data]
   (update-person people name data))
  ([people name data]
   (let [idx (find-index people name)
         person (get @people idx)
         updated (merge person data)]
     (if (not= idx -1)
       (swap! people assoc idx updated)
       (str "could not find index: " idx)))))

