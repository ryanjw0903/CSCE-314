
-- CSCE 314 [Section 500] Programming Languages Spring 2020
-- Homework Assignment 4B Monadic Parsing
-- Due by 10:00 p.m. on Monday, March 23

-- Problem 1 (4 points)
-- Student Name: Ryan Wenham
-- UIN: 6270002098
-- (ACKNOWLEDGE ANY HELP RECEIVED HERE)


module Parsing (module Parsing, module Control.Applicative) where

import Control.Applicative
import Data.Char

-- Basic definitions

newtype Parser a = P (String -> [(a,String)])

parse :: Parser a -> String -> [(a,String)]
parse (P p) inp = p inp
-- checks if empty
item :: Parser Char
item = P (\inp -> case inp of
                     []     -> []
                     (x:xs) -> [(x,xs)])

-- Sequencing parsers

instance Functor Parser where
   -- fmap :: (a -> b) -> Parser a -> Parser b
   fmap g p = P (\inp -> case parse p inp of
                            []        -> []
                            [(v,out)] -> [(g v, out)])

instance Applicative Parser where
   -- pure :: a -> Parser a
   pure v = P (\inp -> [(v,inp)])

   -- <*> :: Parser (a -> b) -> Parser a -> Parser b
   pg <*> px = P (\inp -> case parse pg inp of
                             []        -> []
                             [(g,out)] -> parse (fmap g px) out)

instance Monad Parser where
   -- (>>=) :: Parser a -> (a -> Parser b) -> Parser b
   p >>= f = P (\inp -> case parse p inp of
                           []        -> []
                           [(v,out)] -> parse (f v) out)

-- Making choices

instance Alternative Parser where
   -- empty :: Parser a
   empty = P (\inp -> [])

   -- (<|>) :: Parser a -> Parser a -> Parser a
   p <|> q = P (\inp -> case parse p inp of
                           []        -> parse q inp
                           [(v,out)] -> [(v,out)])

-- Derived primitives

sat :: (Char -> Bool) -> Parser Char
sat p = do x <- item
           if p x then return x else empty

digit :: Parser Char
digit = sat isDigit

lower :: Parser Char
lower = sat isLower

upper :: Parser Char
upper = sat isUpper

letter :: Parser Char
letter = sat isAlpha

alphanum :: Parser Char
alphanum = sat isAlphaNum

char :: Char -> Parser Char
char x = sat (== x)

string :: String -> Parser String
string []     = return []
string (x:xs) = do char x
                   string xs
                   return (x:xs)

ident :: Parser String
ident = do x  <- lower
           xs <- many alphanum
           return (x:xs)

nat :: Parser Int
nat = do xs <- some digit
         return (read xs)

int :: Parser Int
int = do char '-'
         n <- nat
         return (-n)
       <|> nat

-- Handling spacing

space :: Parser ()
space = do many (sat isSpace)
           return ()

token :: Parser a -> Parser a
token p = do space
             v <- p
             space
             return v

identifier :: Parser String
identifier = token ident

natural :: Parser Int
natural = token nat

integer :: Parser Int
integer = token int

symbol :: String -> Parser String
symbol xs = token (string xs)


-- Problem 2.1 (13 points) Explain the step-by-step working of
-- > parse expr "2*(3+4)"
-- resulting in
-- [(14,"")]
-- using the definitions of expr, term and factor given in section 13.8.
{- Write your explanation here
   1. passes nat 2 over to left hand side of list.
   2. Passes expression * to left hand side.
   3. Passes expr (3+4) to left hand and side.
   4. Breaks down (3+4) into terms then factors then nats of 3 and 4
   5. Expression then excuates 3+4 to make 7 and then mutplies by 2 to get 14
   6. Making the left side of the list 14 and the right side a empty string now


-}

-- Problem 2.2 (13 points) Explain the step-by-step working of
-- > parse expr "1+2*3"
-- resulting in
-- [(7,"")]
-- using the definitions of expr, term and factor given in section 13.8.
{- Write your explanation here
   1. passes nat 2 over to left hand side of list.
   2. Passes expression + to left hand side.
   3. Passes nat 3 to left hand side
   4. passes expression + to left side and then the term, factor and nat of 3 behind it
   5. Starting with the last expression of *, it goes 2*3 =6 + 1 = 7
   6. Making the left side of the list 7 and the right side a empty string now
-}

-- Problem 3 (30 points) Parser for Expr

data Expr = Val Int | Add Expr Expr | Mul Expr Expr  deriving Show

expr :: Parser Expr  -- 10 points
expr = do t <- term
   do symbol "+"
      e <- expr
      return Add t e
      <|> return t


term :: Parser Expr  -- 10 points
term = do f <- factor
   do symbol "*"
      t <- term
      return Mul f t
      <|> return f


factor :: Parser Expr  -- 10 points
factor = do symbol "("
      e <- expr
      symbol ")"
      return Val e
      <|> natural


-- Examples that you can use to test your definitions for expr, term and factor
e1 = "2*(3+4)"
-- > parse expr e1
-- should result in 
-- [(Mul (Val 2) (Add (Val 3) (Val 4)),"")]

e2 = "1+2*3"
-- > parse expr e2
-- should result in 
-- [(Add (Val 1) (Mul (Val 2) (Val 3)),"")]

-- Feel free to add more expressions for your test




