-- Ryan Wenham 627002098
-- Help: Haskell Website, Gentle Introduction to Haskell
-- Problem 2 --
lucas :: Int -> Int
lucas 0 = 2
lucas 1 = 1
lucas n = lucas(n-1) + lucas(n-2)

-- Problem 3 --
qsort1 :: Ord a => [a] -> [a]
qsort1 [] = []
qsort1 (x:xs) = qsort1 large ++ [x] ++ qsort1 small
    where
        small = [y | y<-xs, y<=x]
        large = [y | y<-xs, y>x]
-- Step 1 takes 3 as x and separtes into small and larger. Small = [2,3,1], [3] large = [4]. Recusicve 1
-- Step 2 it takes small and does the process again. small = [1], [2] and larger = [3]. A well as making large = [4] into [4] Recuisve 2
-- Step 3 it now takes small = [1] into [1] and larger = [3] into [3]. Recusive 3
-- Step 4 it nows puts all them into a list from from large to small, which makes it descending gorder. It took 3 Recuisve steps

-- Problem 4 --
scalarproduct :: [Int] -> [Int] -> Int
scalarproduct xs ys = sum [ x*y | (x,y) <- zip xs ys]
-- The first step is taking the head from both lists and making them a set (x,y) leaving the rest in their list
-- Second Step it takes the head of each of the two new lists into a point and again leaves the rest in their list
-- It will do this step over and over intill one of the list reaches its end, which in this case is due to the first list and there will only be 3 set of points.
-- Then the code sums up all the multipication between the points. 

-- Problem 5 --
merge :: Ord a => [a] -> [a] -> [a]
merge [] ys = ys
merge xs [] = xs
merge (x:xs) (y:ys) | x < y = x:merge xs (y:ys) | otherwise = y: merge (x:xs) ys


-- Problem 6 -- 
msort :: Ord a => [a] -> [a]
halve :: [a] -> ([a], [a])
halve xs = (take lhx xs, drop lhx xs)
           where lhx = length xs `div` 2
msort []  = []
msort [x] = [x]
msort  xs = merge (msort left) (msort right)
            where (left,right) = halve xs
        
-- Problem 7 --
isElem :: Eq a => a -> [a] -> Bool
isElem a [] = False
isElem a (x:xs) | a == x = True
                | otherwise = isElem a xs

-- Problem 8 --
toSet [] = []
toSet (x:xs)   | isElem x xs = toSet xs
               | otherwise    = x : toSet xs


