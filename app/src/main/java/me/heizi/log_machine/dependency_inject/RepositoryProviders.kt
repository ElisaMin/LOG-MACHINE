package me.heizi.log_machine.dependency_inject

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.heizi.log_machine.annotations.Database
import me.heizi.log_machine.persistence.DefaultDatabase


@InstallIn(ViewModelComponent::class)
@Module
object RepositoryProviders {

    @Provides
    fun getProjectionRepository(@Database db:DefaultDatabase)=db.projects

}