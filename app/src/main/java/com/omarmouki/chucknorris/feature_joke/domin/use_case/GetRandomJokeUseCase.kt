package com.omarmouki.chucknorris.feature_joke.domin.use_case

import com.omarmouki.chucknorris.core.utils.CommonResponse
import com.omarmouki.chucknorris.feature_joke.data.remote.dto.toJoke
import com.omarmouki.chucknorris.feature_joke.domin.model.JokeModel
import com.omarmouki.chucknorris.feature_joke.domin.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRandomJokeUseCase @Inject constructor(
    private val repository: JokesRepository
) {
    operator fun invoke(): Flow<CommonResponse<JokeModel>> = flow {
        emit(CommonResponse.Loading<JokeModel>())
        try {
            val jokes = repository.getRandomJoke().toJoke()
            emit(CommonResponse.Success<JokeModel>(jokes))

        } catch (e: HttpException) {
            emit(CommonResponse.Error<JokeModel>(message = "unknown"))
        } catch (e: IOException) {
            emit(CommonResponse.Error<JokeModel>(message = "no internet"))


        }

    }
}