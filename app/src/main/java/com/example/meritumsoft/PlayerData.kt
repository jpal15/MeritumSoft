package com.example.meritumsoft

import org.simpleframework.xml.*

@Root(name = "club_teams", strict = false)
data class ClubTeams (
    @field:Element(name = "category", required = false)
    var category: Category = Category(),
)

@Root(name = "category", strict = false)
data class Category  @JvmOverloads constructor (
    @field:Element(name = "players", required = false)
    var players: Players = Players(),

    @field:Attribute(name = "category_id", required = false)
    var categoryID: String= "",

    @field:Attribute(name = "kind", required = false)
    var kind: String= "",

    @field:Attribute(name = "term_id", required = false)
    var termID: String= "",

    @field:Attribute(name = "term", required = false)
    var term: String= "",

    @field:Attribute(name = "team_lineup", required = false)
    var teamLineup: String= "",

    @field:Attribute(name = "menu_index", required = false)
    var menuIndex: String= "",

    @field:Attribute(name = "design_index", required = false)
    var designIndex: String= "",

    @field:Attribute(name = "ordnum", required = false)
    var ordnum: String= "",

    @field:Attribute(name = "category_ico", required = false)
    var categoryIco: String= "",

    @field:Attribute(name = "ts", required = false)
    var ts: String= ""
)

@Root(name = "players", strict = false)
data class Players @JvmOverloads constructor (
    @field:ElementList(name = "position", inline = true, required=false)
    var position: ArrayList<Position> = arrayListOf(),
)

@Root(name = "position", strict = false)
data class Position @JvmOverloads constructor (
    @field:ElementList(name = "player", inline = true, required=false)
    var player: ArrayList<Player> = arrayListOf(),

    @field:Attribute(name = "position_id", required = false)
    var positionID: String= "",

    @field:Attribute(name = "term_id", required = false)
    var termID: String= "",

    @field:Attribute(name = "term", required = false)
    var term: String= "",

    @field:Attribute(name = "position_ico", required = false)
    var positionIco: String= "",

    @field:Attribute(name = "ts", required = false)
    var ts: String= "",
)

@Root(name = "player", strict = false)
data class Player @JvmOverloads constructor (
    @field:ElementList(name = "player_data", inline = true, required=false)
    var playerData: ArrayList<PlayerData> = arrayListOf(),

    @field:Attribute(name = "player_id", required = false)
    var playerID: String= "",

    @field:Attribute(name = "ordnum", required = false)
    var ordnum: String= "",

    @field:Attribute(name = "country_2iso", required = false)
    var country2ISO: String= "",

    @field:Attribute(name = "picture", required = false)
    var picture: String= "",

    @field:Attribute(name = "ts", required = false)
    var ts: String= "",

    @field:Attribute(name = "picture_width", required = false)
    var pictureWidth: String= "",

    @field:Attribute(name = "picture_height", required = false)
    var pictureHeight: String= "",

    @field:Attribute(name = "thumbnail", required = false)
    var thumbnail: String= "",

    @field:Attribute(name = "thumbnail_ts", required = false)
    var thumbnailTs: String= "",

    @field:Attribute(name = "thumbnail_width", required = false)
    var thumbnailWidth: String= "",

    @field:Attribute(name = "thumbnail_height", required = false)
    var thumbnailHeight: String= "",

    @field:Attribute(name = "flag_ico", required = false)
    var flagIco: String= "",

    @field:Attribute(name = "flag_ts", required = false)
    var flagTs: String= ""
)

@Root(name = "player_data", strict = false)
data class PlayerData @JvmOverloads constructor (
    @field:Attribute(name = "term_id", required = false)
    var termID: String = "",

    @field:Attribute(name = "term", required = false)
    var term: String= "",

    @field:Attribute(name = "value", required = false)
    var value: String= "",

    @field:Attribute(name = "field_type", required = false)
    var fieldType: String= "",

    @field:Attribute(name = "field_ico", required = false)
    var fieldIco: String= "",

    @field:Attribute(name = "ts", required = false)
    var ts: String= ""
)
