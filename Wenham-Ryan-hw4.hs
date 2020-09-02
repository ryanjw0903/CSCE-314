
-- CSCE 314 [Sec. 500] Programming Languages Spring 2020 Hyunyoung Lee
-- Homework Assignment 4A: Exercise 12.7
-- This is an individual assignment.  Remembr the Aggie Honor Code!
-- You will earn total 40 points in this assignment.
-- Deadline: 10:00 p.m. Monday, March 2, 2020

-- Problem 1 (3 points)
-- Student Name: Ryan Wenham
-- UIN: 627002098
-- 

module Main where

import Test.HUnit
import System.Exit


-- Chapter 12 Exercise 7 (37 points)
data Expr a = Var a | Val Int | Add (Expr a) (Expr a)
              deriving Show

instance Functor Expr where
-- fmap :: (a -> b) -> Expr a -> Expr b
-- P1. [10 points] Give three definitions for fmap for Expr 
   fmap g (Var x) = Var (g x)
   fmap g (Val x) = Val x
   fmap g (Add l r) = Add (fmap g l) (fmap g r)

instance Applicative Expr where
-- pure :: a -> Expr a
 
-- <*> :: Expr (a -> b) -> Expr a -> Expr b
-- P2. [10 points] Give three definitions for (<*>) for Expr
 
    pure = Var
    (Var g) <*> mx = fmap g mx
    (Val x) <*> mx = Val x
    (Add l r) <*> mx = Add (l<*>mx) (r<*>mx)


instance Monad Expr where
-- (>>=) :: Expr a -> (a -> Expr b) -> Expr b
-- P3. [10 points] Give three definitions for (>>=) for Expr
  (Var x) >>= g = g x
  (Val x) >>=g = Val x 
  (Add l r) >>= g = Add (l>>=g) (r>>=g)



-- P4. [7 points] Using the Expr object e1x and the first definition
-- of function g (see below), explain what the (>>=) operator for
-- the Expr type does when you do 
-- > e1x >>= g
-- in GHCi. Be as specific as possible!
{- Write your answer here.
  >>= is called a bind operator, and for the expression is binds whatever function
  operations of to take in what g is and do that operation on the fucntion 
  e1x.
-}

-- Expr objects 
e1x = Add (Val 1) (Var 'x')
e2y = Add (Val 2) (Var 'y')
ez3 = Add (Var 'z') (Val 3) 
exyz = Add (Add (Var 'x') (Var 'y')) (Add (Var 'z') (Var 'y'))

-- Function g definitions
g 'x' = Val 2   -- definition 1
g 'y' = Val 3
g 'z' = Val 4

-- A simplest eval function for tests below
eval :: Expr a -> Int
eval (Val n) = n
eval (Add a b) = eval a + eval b

-- (TestCase assertEqual "e bind g" (e >>= g) e_g)
myTestList = 
  TestList [
     "test 1" ~: eval (e1x >>= g)  ~?= 3
    ,"test 2" ~: eval (e2y >>= g)  ~?= 5
    ,"test 3" ~: eval (ez3 >>= g)  ~?= 7
    ,"test 4" ~: eval (exyz >>= g) ~?= 12
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

