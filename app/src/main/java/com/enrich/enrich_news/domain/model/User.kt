package com.enrich.enrich_news.domain.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Represents user information within the application.
 *
 * @property id The unique identifier for the user. Defaults to 0 if not specified.
 * @property firstName The first name of the user. Defaults to an empty string if not specified.
 * @property lastName The last name of the user. Defaults to an empty string if not specified.
 * @property gender The gender of the user. Defaults to -1 if not specified.
 * @property emailAddress The email address of the user. Defaults to an empty string if not specified.
 */
@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var firstName: String? = "",
    var lastName: String? = "",
    var gender: Int? = -1,
    var emailAddress: String? = "",
) : Parcelable
