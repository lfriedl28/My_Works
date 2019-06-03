//
//  SampleData.swift
//  PlayerGameRatings
//
//  Created by Friedl, Luke on 9/25/18.
//  Copyright © 2018 CVTC. All rights reserved.
//

import Foundation

final class SampleData {
    
    static func genteratePlayersData() -> [Player] {
        return [
            Player(name: "Rick Sanchez", game: "5 card draw", rating: 1),
            Player(name: "Uklar", game: "Knife Fight", rating: 5),
            Player(name: "Harry Potter", game: "Quidditch", rating: 4)
        ]
    }
}
