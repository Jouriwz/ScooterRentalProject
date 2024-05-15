import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {LoginComponent} from "./auth/login/login.component";
import {HomeComponent} from './home/home.component';
import {Router, RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule, HttpHeaders} from "@angular/common/http";

import {PokedexComponent} from './pokedex/pokedex.component';
import {PokemonPartyComponent} from './pokemon-party/pokemon-party.component';
import {PokemonTutorialComponent} from './pokemon-tutorial/pokemon-tutorial.component';
import {PokemonBattleComponent} from './pokemon-battle/pokemon-battle.component';
import {PokemonShopComponent} from './pokemon-shop/pokemon-shop.component';
import {NavigationComponent} from './utils/navigation/navigation.component';
import {PokemonBattleSelectComponent} from './pokemon-battle-select/pokemon-battle-select.component';
import { FusionComponent } from './pokemon-shop/fusion/fusion.component';
import { BattleMenuComponent } from './pokemon-battle/battle-menu/battle-menu.component';
import { EnemyInfoComponent } from './pokemon-battle/enemy-info/enemy-info.component';
import { PlayerInfoComponent } from './pokemon-battle/player-info/player-info.component';
import { EnemyPokemonComponent } from './pokemon-battle/enemy-pokemon/enemy-pokemon.component';
import { PlayerPokemonComponent } from './pokemon-battle/player-pokemon/player-pokemon.component';
import {RegisterComponent} from "./auth/register/register.component";
import { FightComponent } from './pokemon-battle/battle-menu/fight/fight.component';
import { PartyComponent } from './pokemon-battle/battle-menu/party/party.component';
import { BagComponent } from './pokemon-battle/battle-menu/bag/bag.component';
import { SurrenderComponent } from './pokemon-battle/battle-menu/surrender/surrender.component';
import { PartyDashboardComponent } from './home/party-dashboard/party-dashboard.component';
import { AuthComponent } from './auth/auth.component';
import {interceptor} from "../interceptor";
import { ResetPasswordComponent } from './auth/reset-password/reset-password.component';

const appRoutes: Routes = [
    {path: '', component: LoginComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'home', component: HomeComponent},
    {path: 'pokedex', component: PokedexComponent},
    {path: 'battle-select', component: PokemonBattleSelectComponent},
    {path: 'battle', component: PokemonBattleComponent},
    {path: 'party', component: PokemonPartyComponent},
    {path: 'tutorial', component: PokemonTutorialComponent},
    {path: 'shop', component: PokemonShopComponent},
    {path: 'reset-password', component: ResetPasswordComponent}
]

@NgModule({
    declarations: [
        AppComponent,
        AuthComponent,
        LoginComponent,
        RegisterComponent,
        HomeComponent,
        PokedexComponent,
        PokemonPartyComponent,
        PokemonTutorialComponent,
        PokemonBattleComponent,
        PokemonShopComponent,
        NavigationComponent,
        PokemonBattleSelectComponent,
        FusionComponent,
        BattleMenuComponent,
        EnemyInfoComponent,
        PlayerInfoComponent,
        EnemyPokemonComponent,
        PlayerPokemonComponent,
        FightComponent,
        PartyComponent,
        BagComponent,
        SurrenderComponent,
        PartyDashboardComponent,
        ResetPasswordComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(appRoutes),
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: interceptor,
            multi: true,
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
