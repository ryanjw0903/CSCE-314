
-- CSCE 314 [Section 500] Programming Languages Spring 2020
-- Homework Assignment 2

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Ryan Wenham
-- UIN: 627002098
-- Textbook, Haskell.org

module Main where

import Prelude hiding ((&&))

import Test.HUnit
import System.Exit
import Data.Char



-- Problem 2 (5 points) Chapter 4, Exercise 5
(&&) :: Bool -> Bool -> Bool
(&&) a b = if a then if b then True
         else  False
         else  False


-- Problem 3 (10+10 = 20 points) Chapter 4, Exercise 8
luhnDouble :: Int -> Int  -- 10 points
luhnDouble x = if x*2 < 9 then x*2 else (x*2) - 9

luhn :: Int -> Int -> Int -> Int -> Bool  -- 10 points
luhn a b c d  | mod (luhnDouble a + b + luhnDouble c + d) 10 == 0 = True
              | otherwise = False


-- Problem 4 (10 points) Chapter 5, Exercise 6
factors :: Int -> [Int]
factors n = [a | a <- [1..n], mod n a == 0]

perfects :: Int -> [Int]
perfects n = [a | a <- [1..n], sum(init(factors a)) == a ]



-- Problem 5 (7+7+6 = 20 points) Chapter 6, Exercise 5
{- Write your answer in this block comment neatly and clearly.
Using the recursive definitions given in this chapter, show how length [1,2,3], drop 3
[1,2,3,4,5], and init [1,2,3] are evaluated.

1. length [1,2,3]
 1 + (length [2,3]) -> 1 + (1 + (length[3])) -> 1 + (1 + (1 + length []) -> 1+1+1+0 = 3

2. drop 3 [1,2,3,4,5]
  drop 2 [2,3,4,5] -> drop 1 [3,4,5] -> drop 0 [4,5] -> [4,5]
 

3. init [1,2,3]
  1 : init [2,3] -> 1:2:intit[3] -> 1:2:[] = [1,2]
   
  
-}


-- Problem 6 (8+7=15 points)
halve :: [a] -> ([a], [a])
halve xs = (take lhx xs, drop lhx xs)
           where lhx = length xs `div` 2

mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]  -- 8 points
mergeBy op [] [] = []
mergeBy op xs [] =xs
mergeBy op [] ys = ys
mergeBy op (x:xs) (y:ys) = case op x y of
                          True -> x:mergeBy op xs (y:ys)
                          False -> y:mergeBy op (x:xs) ys


msortBy :: (a -> a -> Bool) -> [a] -> [a]  -- 7 points
msortBy op [] =[]
msortBy op [x] = [x]
msortBy op xs = mergeBy op (msortBy op left) (msortBy op right)
            where (left,right) = halve xs


-- Problem 7 (10+5 = 15 points) Chapter 7, Exercise 9
altMap :: (a -> b) -> (a -> b) -> [a] -> [b]  -- 10 points
altMap _ _ [] = []
altMap x _ (a:[]) = x a : []
altMap x y (a:b:[]) = x a : y b : []
altMap x y (a:b:cs) = x a : y b : altMap x y cs


{- (5 points)
   Explain how your altMap works when it is applied as below.
   > altMap (*2) (`div` 2) [0..6,7]
   The list is numbers [0,1,2,3,4,5,6]. Starting with the first digit *2 applies
   and div 2 applies to the second digit. It does this pattern of repating till the end of 
    of the list. When dividing by three it will round down because they are ints.

-}


-- Problem 8 (10 points)
concatenateAndUpcaseOddLengthStrings :: [String] -> String
concatenateAndUpcaseOddLengthStrings xs =  map toUpper(concat(filter (\xs -> odd (length xs)) xs))

myTestList = 
  TestList [
  
      "&& 1" ~: (&&) True True ~=? True
    , "&& 2" ~: (&&) True False ~=? False    
    , "&& 3" ~: (&&) False True ~=? False
    , "&& 4" ~: (&&) False False ~=? False
    
    , "luhnDouble 1" ~: luhnDouble 3 ~=? 6
    , "luhnDouble 2" ~: luhnDouble 6 ~=? 3
    , "luhnDouble 3" ~: luhnDouble 5 ~=? 1

    , "luhn 1" ~: luhn 1 7 8 4 ~=? True
    , "luhn 2" ~: luhn 4 7 8 3 ~=? False
    
    , "perfects 1" ~: perfects 500 ~=? [6,28,496]

    , "mergeBy 1" ~: mergeBy (>) "FED" "GBA" ~=? "GFEDBA"
    , "mergeBy 2" ~: mergeBy (<) "Howdy" "Maui" ~=? "HMaouiwdy"
      
    , "msortBy 1" ~: msortBy (<) "gig 'em" ~=? " 'eggim" 
    , "msortBy 2" ~: msortBy (>) "Jack be nimble" ~=? "nmlkieecbbaJ  "
    , "msortBy 3" ~: msortBy (<) "" ~=? ""

    , "altMap 1" ~: altMap (* 10) (* 100) [1,2,3,4,5] ~=? [10,200,30,400,50]
    , "altMap 2" ~: and (altMap even odd [1..10]) ~=? False
    , "altMap 3" ~: altMap toLower toUpper "Haskell IS fun!" ~=? "hAsKeLl iS FuN!"

    , "concatenateAndUpcaseOddLengthStrings" ~:
         concatenateAndUpcaseOddLengthStrings ["here's ", "an ", "a ", "example"] ~=? "HERE'S AN EXAMPLE" 

    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)
          
codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess

