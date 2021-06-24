package org.skyfaced.todi.di.modules

import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import org.skyfaced.todi.services.public.PublicService
import org.skyfaced.todi.services.public.PublicServiceImpl
import org.skyfaced.todi.services.user.UserRepository
import org.skyfaced.todi.services.user.UserService
import org.skyfaced.todi.services.user.UserServiceImpl

val serviceModule = module {
    single { UserRepository(get()) }
    singleBy<UserService, UserServiceImpl>()

    singleBy<PublicService, PublicServiceImpl>()
}
