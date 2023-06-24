trait Functor[F[_]]:
  extension [A](x: F[A])
    def ffmap[B](f: A => B): F[B]

/*

  type StringEither[A] = Either[String, A]
  given Functor[StringEither] with
    extension [A](x: StringEither[A])
      def ffmap[B](f: A => B): StringEither[B] =
        x match
          case Right(a) => Right(f(a))
          case Left(err) => Left(err)

*/

// Type Lambdas
given eitherFunctor[E]: Functor[[A] =>> Either[E, A]] with
  extension [A](x: Either[E, A])
    def ffmap[B](f: A => B): Either[E, B] = 
      x match
        case Right(a) => Right(f(a))
        case Left(err) => Left(err)



@main def m() =
  val myInt: Either[String, Int] = Right(2)
  val myError: Either[String, Int] = Left("oops")

  val mappedInt = myInt.ffmap(_ + 1)
  val mappedError = myError.ffmap(_ + 1)

  println(mappedInt)
  println(mappedError)
